| Situation | Rôle destinataire | CellEvent envoyé |
|--------------|:----------------:|--------------:|
| La sortie est positionnée en 2,3 | MONSTER | 1, EXIT, (2,3) |
| Le chasseur tire en 6,7 | MONSTER | 3, HUNTER, (6,7) |
| Le chasseur tire en 6,7 | HUNTER | 3, EMPTY, (6,7) |
| Le chasseur tire sur un passage du monstre en (1,2) | HUNTER | 4, MONSTER, (1,2) |
| Le chasseur tire sur un passage du monstre en (1,2) | MONSTRE | 4, HUNTER, (1,2) |
| Le chasseur tire sur un mur en (7,6) | HUNTER | 5, WALL, (7,6) |
| Le chasseur tire sur un mur en (7,6) | MONSTRE | 5, HUNTER, (7,6) |
| Le chasseur tire sur le monstre en (4,5) | HUNTER | 6, MONSTRE, (4,5) |