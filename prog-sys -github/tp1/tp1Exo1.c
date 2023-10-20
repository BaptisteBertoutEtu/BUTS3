#include <assert.h>
#include <stdio.h>
#define TAILLE_PILE 5

typedef int element_t;

// Une pile de taille fixe
typedef struct {
    // un tableau d'éléments
    element_t pile[TAILLE_PILE];
    // l'indice du dernier élément empilé
    int sommet;
} pile_fixe_t;

/// Initialise une pile. La pile
/// doit être vide après initialisation
void pile_fixe_initialiser(pile_fixe_t *pile);

/// Retourne vrai si la pile est vide, 0 sinon
/// (on rappelle qu'en C, 0 est faux, tout le reste est vrai)
int pile_fixe_est_vide(const pile_fixe_t *pile);

/// Ajoute un élément dans la pile pointée par pile
///
/// Retourne 0 si l'élément a pu être ajouté, -1 sinon
/// (en cas de pile pleine par exemple)
int pile_fixe_empiler(pile_fixe_t *pile, element_t element);

/// Supprime un élément de la pile et le copie
/// à l'adresse pointée par p_element.
///
/// Retourne 0 en cas de succès, -1 sinon
/// (si la pile était vide par exemple)
int pile_fixe_depiler(pile_fixe_t *pile, element_t *p_element);

/// Affiche les éléments contenus dans la pile
void pile_fixe_afficher(const pile_fixe_t *pile);

/// Retourne 1 si la pile est pleine, 0 sinon
int pile_fixe_est_pleine(const pile_fixe_t *pile);

void test_pile_fixe(void);



int main(void){
    test_pile_fixe();
    return 0;
}

void pile_fixe_initialiser(pile_fixe_t *pile){
    pile->sommet = 0; 
}

int pile_fixe_est_vide(const pile_fixe_t *pile){
    if (pile->sommet == 0){return 1;}
    else{ return 0;}
}

int pile_fixe_est_pleine(const pile_fixe_t *pile){
    if(pile->sommet == TAILLE_PILE){
        return 1;
    }
    else{return 0;}
}

int pile_fixe_empiler(pile_fixe_t *pile, element_t element){
    if(pile_fixe_est_pleine(pile) == 1){
        return -1;
    }
    else{
        pile->pile[pile->sommet] = element;
        pile->sommet = pile->sommet+1;
        return 0;
    }
}

int pile_fixe_depiler(pile_fixe_t *pile, element_t *p_element){
    if(pile_fixe_est_vide(pile) == 1){
        return -1;
    }
    else{
        pile->sommet = pile->sommet-1;
        *(p_element) = pile->pile[pile->sommet];
        return 0;
    }
    
}

void pile_fixe_afficher(const pile_fixe_t *pile){
    for (int i = 0; i < TAILLE_PILE; i++){
        printf("element : %d\n",pile->pile[i]);
    }
}





void test_pile_fixe(void) {

    pile_fixe_t pile;

    pile_fixe_initialiser(&pile);

    // Une pile initialisée doit être vide
    assert(pile_fixe_est_vide(&pile));

    assert(pile_fixe_empiler(&pile, 1) == 0);
    assert(pile_fixe_empiler(&pile, 2) == 0);
    assert(pile_fixe_empiler(&pile, 3) == 0);
    assert(pile_fixe_empiler(&pile, 4) == 0);
    assert(pile_fixe_empiler(&pile, 5) == 0);
    assert(pile_fixe_empiler(&pile, 6) == -1);

    pile_fixe_afficher(&pile);

    element_t e;
    assert(pile_fixe_depiler(&pile, &e) == 0);
    assert(e == 5);
    assert(pile_fixe_depiler(&pile, &e) == 0);
    assert(e == 4);
    assert(pile_fixe_depiler(&pile, &e) == 0);
    assert(e == 3);
    assert(pile_fixe_depiler(&pile, &e) == 0);
    assert(e == 2);
    assert(pile_fixe_depiler(&pile, &e) == 0);
    assert(e == 1);

    assert(pile_fixe_depiler(&pile, &e) == -1);

    assert(pile_fixe_est_vide(&pile));
}