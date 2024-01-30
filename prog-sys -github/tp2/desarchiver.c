#include <stdint.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>


// Copie un fichier désigné par le descripteur source
// dans un fichier désigné par le descripteur destination en copiant taille octets
// retourne le nombre d'octets copiés en cas de succès, -1 en cas d'erreur
int copier(int source, int destination, int taille);



// Extrait le prochain fichier contenu dans l'archive. Si un fichier
// portant le même nom que celui indiqué dans l'archive existe déjà il
// doit être écrasé.
//
// Retourne -1 en cas d'erreur et le nombre d'octets du fichier
// extrait en cas de succès
int extrait_fichier(int fd_archive);


// Extrait l'archive dans le répertoire courant.
//
// Retourne -1 en cas d'erreur, le nombre de fichiers créés en cas de
// succès.
int extrait_archive(const char *archive);

int main(void){
    extrait_archive("fichiers_textes.arch");
    extrait_archive("fichiers_binaires.arch");
    return 0;
}


// Extrait l'archive dans le répertoire courant.
//
// Retourne -1 en cas d'erreur, le nombre de fichiers créés en cas de
// succès.
int extrait_archive(const char *archive){

    // ouverture de l'archive
    int source = open(archive, O_RDONLY);
    if(source == -1){
        perror(archive);
        return 1;
    }

    //lecture du nombre de fichier indiqué par un entier 32 bits non signé au format little endian
    uint32_t nb_fichiers;
    int taille = read(source, &nb_fichiers, sizeof(uint32_t));
    if (taille==-1) return -1;

    //extraction de l'ensemble des fichier selon le nombre de fichier présent dans l'archive
    for (uint32_t i = 0; i < nb_fichiers; i++){
        extrait_fichier(source);
    }
    
    //fermeture de la source
    close(source);

    //return 0 si tout s'est bien passé
    return 0;
}



// Extrait le prochain fichier contenu dans l'archive. Si un fichier
// portant le même nom que celui indiqué dans l'archive existe déjà il
// doit être écrasé.
//
// Retourne -1 en cas d'erreur et le nombre d'octets du fichier
// extrait en cas de succès
int extrait_fichier(int fd_archive){

    //lecture de la taille du nom définit par un entier 8 bits non signés
    uint8_t taille_du_nom;
    char valeur_du_nom[256];

    //lecture de la taille du nom du fichier
    int nb_octets_lu_nom;
    nb_octets_lu_nom = read(fd_archive, &taille_du_nom, sizeof(uint8_t));

    //on verifie si la taille lue n'est pas null.
    if (nb_octets_lu_nom==-1) return -1;

    //lecture du nom du fichier
    nb_octets_lu_nom = read(fd_archive, &valeur_du_nom, taille_du_nom);
    //on vérifie que le nom n'est pas null.
    if (nb_octets_lu_nom==-1) return -1;

    //on ajoute le caractère permettant de définir la fin d'une chaine de caractère
    valeur_du_nom[taille_du_nom] = '\0';

    //on lit le fichier/ le créé si pas créé/ le remplace si existant
    int nouveau_fichier = open(valeur_du_nom, O_RDWR|O_CREAT|O_TRUNC, 0644);

    //on verifie que le fichier existe
    if(nouveau_fichier==-1) return 1;

    // on lit la taille totale du contenu du fichier
    uint64_t taille_du_texte;
    int nb_octets_lu_texte;
    nb_octets_lu_texte = read(fd_archive, &taille_du_texte, sizeof(uint64_t));

    //on verifie que le contenu est bien lu
    if (nb_octets_lu_texte==-1) return -1;

    //on copie le contenu du fichier dans le fichier lui meme grace a la taille obtenu au dessus
    copier(fd_archive,nouveau_fichier,taille_du_texte);

    // on retourne 0 si tout s'est bien passé
    return 0;
}


// Copie un fichier désigné par le descripteur source
// dans un fichier désigné par le descripteur destination en copiant taille octets
// retourne le nombre d'octets copiés en cas de succès, -1 en cas d'erreur
int copier(int source, int destination, int taille){

    // on verifie que la source et al destination existe
    if(source == -1 || destination == -1) return -1;

    //on définit la variable prenant le contenu du fichier.
    char valeur_du_text[taille];

    // on lit le contenu du fichier et on place le resultat dans la variable valeur_du_text
    int resul = read(source, valeur_du_text, taille);

    // on vérifie que le contenu qui a été lu n'est pas null
    if(resul == -1) return -1;

    // on écrit dans le fichier destination le contenu du text
    return write(destination, valeur_du_text, resul);
}