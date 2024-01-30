---
title: Rapport développement efficace
author: Baptiste BERTOUT, Mathis DECOSTER, Arthur KELLER, Pierre PLANCHON, Gaspard SOULIEZ
header-includes: 
  - \usepackage{fancyhdr}
  - \pagestyle{fancy}
  - \fancyhead{}
  - \fancyhead[C]{SAE3.02 - Dévoloppement d'applications}
  - \fancyhead[R]{\thepage}
  - \fancyfoot{}
  - \fancyfoot[C]{\thepage}
  - \renewcommand{\headrulewidth}{0.4pt}
  - \renewcommand{\footrulewidth}{0pt}
---
\newpage

# Description
## Génération de labyrinthe
### Algorithme utilisé
### Structures de données
### Efficacité

## IA
### Monster
L'intelligence artificielle (IA) du monstre repose sur un algorithme de parcours en largeur pour déterminer le chemin optimal vers la sortie du labyrinthe. L'implémentation de cet algorithme est réalisée dans la classe `IAMonstre`.

#### Algorithme de parcours en largeur
L'algorithme de parcours en largeur est utilisé pour trouver le plus court chemin entre la position actuelle du monstre et la sortie du labyrinthe. La méthode `findShortestPath` prend en compte la structure de données d'une file (Queue) pour explorer les différentes possibilités de déplacement.
```java
private List<ICoordinate> findShortestPath(ICoordinate start, ICoordinate goal) {
    // ... [Détailler le fonctionnement de l'algorithme]
}
```

#### Choix du prochain mouvement
La méthode `chooseNextMove` utilise le chemin trouvé par l'algorithme de parcours en largeur pour déterminer le prochain mouvement du monstre. Si un chemin est trouvé, le monstre se déplace vers la deuxième coordonnée du chemin, fournissant ainsi une stratégie intelligente. En l'absence de chemin, le monstre effectue un mouvement aléatoire parmi les possibilités.
```java
private ICoordinate chooseNextMove() {
    // ... [Expliquer la logique du choix du prochain mouvement]
}
```

#### Structures de données utilisées
L'implémentation de l'IA du monstre utilise une file (*Queue*) et un ensemble (*Set*) pour suivre les mouvements déjà explorés et optimiser la recherche du chemin le plus court.
```java
Queue<List<ICoordinate>> queue = new LinkedList<>();
Set<ICoordinate> visited = new HashSet<>();
```

### Hunter
L'intelligence artificielle du Hunter repose sur un système de portée, dans un premier temps il cherche une pise en tirant aléatoirement puis si il trouve une trace du monster il estime une zone dans laquelle il peut tirer aléatoirement pour avoir plus de probabilités de trouver le monster. Il est situé dans la classe `IAHunter`.
pandoc -f markdown -t pdf Rapport.md -o Rapport.pdf --number-sections --toc --metadata title="Rapport développement efficace" -V colorlinks=true -V linkcolor=cyan -V urlcolor=cyan