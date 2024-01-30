# **Groupe G5 :**

## **Liste des membres :**

- Baptiste BERTOUT
- Pierre PLANCHON
- Arthur KELLER
- Gaspard SOULIEZ
- Mathis DECOSTER

## Lancement du Jeu

Pour démarrer notre jeu, c'est très simple. Il vous suffit simplement de taper la commande suivante dans votre terminal :  

```bash
java -jar G5_SAE3A.jar
```

## Les fonctionnalités du jeu

### Jouer

Vous pouvez cliquer sur **Jouer**. Cela vous permettra de débuter une partie avec les paramètres par défaut.

Lorsque vous jouez, vous avez la possibilité d'enregistrer le labyrinthe si vous voulez le retrouver pour une autre partie. En sachant que le Monstre et la sortie ne seront surement par au même endroit qu'au début de la partie.

### Créer son labyrinthe

Vous avez la possibilité de créer, directement, votre propre labyrinthe.

Dans cette partie il vous sera d'abord demandé de choisir la taille de votre labyrinthe. Celle-ci sera comprise entre 10 et 30 pour la largeur et 8 et 20 pour la hauteur.

Suite à cela vous aurez accès à une grille avec des cases cliquables. Celles-ci changeront de couleurs lorsqu'elles changent d'état. Lorsqu'une d'entre elles devient noires, cela veut dire que c'est un mûr, au contraire si elle est blanche c'est que c'est une case vide.

Par ailleurs, les cases disposées au coins sont incangeable. En effet, elles sont réservé pour placer la sortie.

Dans cette création de labyrinthe, vous devez faire attention à toujours avoir un chemin d'accès à au moins un des quatres coins de la grille, si vous ne respectez pas cela, vous prenez le risque de faire planter le jeu et de perdre en expériences d'utilisation.

Une fois cela fini vous pouvez sauvgarder votre labyrinthe et retourner au menu. Si le labyrinthe ne s'enregistre pas, vous serez averti. 
Par ailleurs, si votre labyrinthe est enregisté, vous devez tout de même vous rendre dans les paramètres pour choisir de jouer avec votre propre labyrinthe.

### Modifier les paramètres

Vous avez également la possibilité de cliquer sur **Modifier les paramètres**. Cette option vous permettra de personnaliser les paramètres de votre partie.

#### Mode de jeu

Dans cette partie des paramètres, vous pouvez choisir le mode de jeu. En effet, vous pouvez jouer avec plusieurs mode :

- PvP : Player vs Player, c'est à dire vous contre votre ami.
- IAvP : IA vs Player, ici encore vous avez deux choix soit vous en tant que Monstre et une IA en Chasseur ou vous en Chasseur et une IA en Monstre.
- IAvIA : IA vs IA, c'est un dire un combat entre les deux IA.

#### Paramètres du labyrinthe

##### Choix du labyrinthe

Dans cette partie vous allez pouvoir choisir le labyrinthe que vous voulez utiliser pour jouer.

Vous avez deux choix :

- Labyrinthe aléatoire
- Labyrinthe prédéfini

Le labyrinthe aléatoire est généré aléatoirement en fonction de la taille du labyrinthe voulu. Le Monstre est positionné aléatoirement sur le plateau. et la sortie est positionnée sur l'un des quatres coins.

Le labyrintes prédéfini est un labyrinthe créé par vous même ou l'un des trois fourni par nos soins.
Les labyrinthes fournis par nos soins ont trois taille : petit, moyen ou grand.

L'ensemble des labyrinthes se trouvent dans le répertoire "res/labyPredef/". Et l'ensemble de ces labyrinthes seront lystés au chargement de cette page.

Si vous voulez ajouter votre propre labyrinthe sans utiliser l'option de création implémentée dans le jeu, vous vous devez de respecter certaines règles :

- Les mùrs sont représentés par le caractères '#' et les cases vides par un espace.
- Les tailles maximales sont de 30 de largeur et 20 de hauteur, et sont à respecter.
- Les tailles minimales sont de 10 de largeur et 8 de hauteur, et sont à respecter.
- Un chemin doit toujours existé vers au moins un des quatres coins.
- Au moins un des quatres coins doit être une case libre.
- Une proportion de mûrs trop importante augmente le risque de bug.

##### Taille du labyrinthe

Dans cette partie, vous pouvez choisir la taille du labyrinthe que vous voulez généré. A savoir que cette taille n'est utilisée que dans le cas ou vous choisissez le labyrinthes aléatoire.

La taille maximale pour la largeur est de 30 et la taille minimale est de 10.
La taille maximale pour la hauteur est de 20 et la taille minimale est de 8.

Pour le bon fonctionnement de cette page, si vous saisissez une valeur à l'aide de votre clavier, il faut impérativement appuyer sur la touche "Entrer" pour valider ce que vous venez d'écrire, dans le cas contraire, ce que vous avez saisi ne sera pas pris en compte bien qu'afficher à l'écran.

##### Apparition de mûrs

Dans cette partie, vous pouvez choisir le poucentage d'apparition des mùrs du labyrinthe que vous voulez généré. A savoir que cette taille n'est utilisée que dans le cas ou vous choisissez le labyrinthes aléatoire.

Le pourcentage maximale est de 0.5 (50%) et la taille minimale est de 0.15 (15%).

Pour le bon fonctionnement de cette page, si vous saisissez une valeur à l'aide de votre clavier, il faut impérativement appuyer sur la touche "Entrer" pour valider ce que vous venez d'écrire, dans le cas contraire, ce que vous avez saisi ne sera pas pris en compte bien qu'afficher à l'écran.

#### Paramètre de déplacement

Dans cette partie, vous pouvez choisir parmi trois choix :

- Mouvement en 8 sans possibilité de "passer" les mûrs.
- Mouvement en 8 avec la possibilité de "passer" les mûrs.
- Mouvement en 4.

Les mouvements sont défini par le nombre de cases que vous pouvez atteindre. Voici une explication :

En 8 : 

    x | x | x   x = case atteignable
    x | M | x   M = position du Monstre
    x | x | x

En 4 :

      | x |     x = case atteignable
    x | M | x   M = position du Monstre
      | x |     case vide = case non-atteingnable

La possibilité de "passer" les mùrs sont expliqués comme ci-dessous :

Possible : 

    x |   | W   x = case de destination
    W | M |     M = position du Monstre
    W |   |     W = mùr

Impossible :

    x | W |     x = case de destination
    W | M |     M = position du Monstre
      |   |     W = mùr

#### Paramètre de vue

Dans cette partie, vous pouvez choisir le mode de vue pour le Monstre.

Vous avez le choix entre deux mode de jeu :

- Connaissance complète
- Connaissance partielle

La connaissance complète signifie que vous connaissez et que vous voyez l'ensemble du labyrinthe.
La connaissance partielle signifie que vous ne connaissez que les cases définis dans le rayon d'action.

Ce rayon d'action se définit dans cette partie également.
Ca valeur minimale est de 2 et sa valeur maximale est de 5.

### Lire les règles

Une autre option est **Lire les règles**. Vous y trouverez les règles du jeu Chasse au Monstre, incluant :

- Les conditions de victoire pour le monstre et le chasseur
- Les connaissances du monstre et du chasseur
- Les tours de jeu du monstre et du chasseur
- Un exemple de tour de jeu

## Documentation du code

Vous pouvez retrouver l'ensemble de la documentation du code en vous rendant dans le répertoire "doc/" et en ouvrant le fichier "index.html".
