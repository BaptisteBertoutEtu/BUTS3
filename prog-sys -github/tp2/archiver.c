#include <stdio.h>
#include <stdint.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>

#define TAILLE_BUFF 10

// Retourne la taille d'un fichier désigné par le descripteur fd, à l'aide
// de la fonction lseek/
// Le curseur associé au fichier doit être replacé à sa place d'origine
// Retourn -1 en cas d'erreur, la taille du fichier en cas de succès
int taille_fichier(int fd);


// Copie entierement un fichier désigné par le descripteur source
// dans un fichier désigné par le descripteur destination
// retourne le nombre d'octets copiés
int copier(int source, int destination);

// Ajoute le fichier désigné par le chemin fichier dans l'archive
// désigné par le descripteur fd_archive
//
// Retourne -1 en cas d'erreur et le nombre d'octets écrits dans
// l'archive en cas de succès
int archive_un_fichier(int fd_archive, const char *fichier);


// Créé un fichier archive à partir d'une liste de fichier source.
// Ecrase le fichier destination s'il existe déjà
//
// Retourne -1 en cas d'erreur et la taille de l'archive finale en
// nombre d'octets en cas de succès
int creer_archive(const char *fichier_archive, char **liste_fichiers, uint32_t nb_fichiers);



int main(int argc, char *argv[]){
    creer_archive(argv[1] , &argv[2] , argc-2);
    return 0;
}

// Retourne la taille d'un fichier désigné par le descripteur fd, à l'aide
// de la fonction lseek/
// Le curseur associé au fichier doit être replacé à sa place d'origine
// Retourn -1 en cas d'erreur, la taille du fichier en cas de succès
int taille_fichier(int fd){
    //position courrante du curseur
    int old = lseek(fd,0,SEEK_CUR);

    //position finale du curseur (ce qui permet de compter le nombre d'octets depuis le debut du fichier)
    int end = lseek(fd,0,SEEK_END);
    if(old == -1 || end == -1 || lseek(fd,old,SEEK_SET) == -1) return -1;

    //on retourne la taille du fichier
    return end;
}

// Copie entierement un fichier désigné par le descripteur source
// dans un fichier désigné par le descripteur destination
// retourne le nombre d'octets copiés
int copier(int source, int destination){
    //on veifie que le fichier source et le fichier destination existent bien
    if(source == -1 || destination == -1) return -1;

    // on alloue assez de place pour contenir l'ensemble du contenu du fichier
    char* buff = malloc(TAILLE_BUFF*sizeof(char));

    //si le buffer n'est pas alloué on libère l'espace
    if(*(buff) == -1){
        free(buff);
        return -1;
    }

    //variable temporaire contenant la taille total du contenu qui va etre lu au fur et a mesure
    int temp = 0;

    //variable permettant de verifier que l'ecriture a fonctionné
    int dest;

    //variable contenant la taille du fichier
    int taille = taille_fichier(source);

    while(temp < taille){

        //on lit petit a petit
        int copie = read(source, buff, TAILLE_BUFF);
        if(copie == -1){
            free(buff);
            return -1;
        }

        // on ajoute a la variable temporaire la taille de ce qui vient d'etre lu
        temp += copie;

        // on ecrit dans le fichier de destination 
        dest = write(destination, buff, copie);
        if(dest == -1){
            free(buff);
            return -1;
        }
    }

    //apres avoir tout ecrit on libere l'espace du buffer
    free(buff);
    return temp;
}


// Ajoute le fichier désigné par le chemin fichier dans l'archive
// désigné par le descripteur fd_archive
//
// Retourne -1 en cas d'erreur et le nombre d'octets écrits dans
// l'archive en cas de succès
int archive_un_fichier(int fd_archive, const char *fichier){

    // on ouvre l'archive
    int f = open(fichier,O_RDONLY,0666);
    if(f == -1){
        close(f);
        return -1;
    }

    //On definir la taille du fichier a ecrire dans l'archive
    uint64_t taille = taille_fichier(f);

    //on définit la taille du nom du fichier pour l'ecrire dans l'archive
    int taille_nom = strlen(fichier);

    // on tentye d'ecrire le taille du nomn du fichie, le nom du fichier et la taille du contenu
    if(write(fd_archive,&taille_nom,sizeof(uint8_t)) == -1 || write(fd_archive,fichier,taille_nom) == -1 ||  write(fd_archive,&taille,sizeof(uint64_t)) == -1){
        close(f);
        return -1;
    }

    // on copie dans l'archive le contenu du fichier
    int nb_octect = copier(f,fd_archive);

    // on ferme l'archive
    close(f);
    return nb_octect;
}


// Créé un fichier archive à partir d'une liste de fichier source.
// Ecrase le fichier destination s'il existe déjà
//
// Retourne -1 en cas d'erreur et la taille de l'archive finale en
// nombre d'octets en cas de succès
int creer_archive(const char *fichier_archive, char **liste_fichiers, uint32_t nb_fichiers){

    //on ouvre l'archive/la créé si non existante/ la remplace si deja existante
    int fd_archive = open(fichier_archive, O_WRONLY | O_CREAT | O_TRUNC, 0666);
    if(fd_archive == -1){
        close(fd_archive);
        return -1;
    }

    printf("Création archive %s :\n",fichier_archive);

    // on ecrit dans le fichier le nombre total de fichier qu'elle va contenir
    if(write(fd_archive,&nb_fichiers,sizeof(uint32_t)) == -1){
        close(fd_archive);
        return -1;
    }

    // pour tous les fichiers present dans le repertoire, on l'archive dans l'archive
    for(uint32_t i=0;i<nb_fichiers;i++){
        if(archive_un_fichier(fd_archive,liste_fichiers[i]) == -1){
            close(fd_archive);
            return -1;
        }
        printf("fichier archivé : %s\n",liste_fichiers[i]);
    }

    int taille = taille_fichier(fd_archive);
    close(fd_archive);
    return taille;
}