# Plage d'adresse IP :

Baptiste : 10.42.116.1-254  
Pierre : 10.42.171.1-254

# Connexion à distance

## Machine de virtualisation :

* dattier.iutinfo.fr

## Mot de passe :

* Le même que pour la session

## Création d'une paire de clé :

Dans la machine physique :  
Commande : `ssh-keygen`  
Nom du fichiier : `/home/infoetu/baptiste.bertout.etu/.ssh/id_rsa`  
Mot de passe : Même que pour Git.  
L'identification a été saugarder dans le fichier : `/home/infoetu/baptiste.bertout.etu/.ssh/id_rsa`  
La clé publique a été sauvgardée dans le fichier : `/home/infoetu/baptiste.bertout.etu/.ssh/id_rsa.pub`  

## Transmission de la clé publique au serveur : 

Utilisation de la commande : `ssh-copy-id -i /home/infoetu/baptiste.bertout.etu/.ssh/id_rsa.pub dattier.iutinfo.fr`  


# Créer et gérer des machines virtuelles

ATTENTION : Toutes les commandes qui vont être effectué doivent être effectuées dans la machine de virtualisation.  

## Crétion d'une machine virtuelle

* Commande : `vmiut creer matrix`  

* Lister les machines virtuelles : `vmiut lister`  

* Démarrer la machine virtuelle : `vmiut demarrer [nom de la machine]`

* Arrêter une machine virtuelle : `vmiut arreter [nom de la machine]`

* Supprimer une machine virtuelle : `vmiut supprimer [nom de la machine]`

* Avoir les infos sur une machine virtuelle : `vmiut info [nom de la machine]`

## Quelques informations sur le réseau et la VM :

Machine de virtualisation 10.42.0.1  
Routeur, DNS 10.42.0.1  
Adresses dynamiques (attribuées automatiquement) 10.42.1.0-10.42.99.255  

La machine virtuelle a été créée à partir d’un modèle. Voici les caractéristiques du modèle:  
* Distribution: Debian GNU/Linux 12 (bookworm)
* Utilisateur standard: user, mot de passe: user
* Administrateur: root, mot de passe: root

* Empreinte des clés SSH serveur:  
  SHA256:C+oy3vfY9fGCAmwzHCUADu75cFUiOGpp7Y5/zOLJIB4 (RSA)  
  SHA256:jq4fycPE9bXnOsphH/mkP0ue3KLQP4WEFmXDuYCpLf0 (ECDSA)  
  SHA256:5CmKzEIqY6qbp0w+sXfHe7/jUDjsPtySwcioO5+BeVo (ED25519)  

## Utilisation de la VM en console virtuelle :

Utilisation de la console virtuelle : `vmiut console matrix`  
Cette commande renvoie une erreur, on doit donc utiliser la commande `ssh -X [nom de la machine de virtualisation]` dans la machine physique.  

## Utilisation de la VM en SSH :

Utilisation de la commande : `ssh user@[IP]` en remplaçant IP par l'ip de la machine (*informations de la machine*)  

## Changement de la configuration réseau :

Utiliser la plage d'adresse qui nous est attribuée.  

* Activer la console virtuelle de la machine.
* Se connecter en tant que `root`.
* Couper l'interface réseau : `ifdown [nom de l'interface]`
* Modifiez les fichiers `/etc/network/interfaces` et `/etc/resolv.conf` de façon à ce que la VM ait une adresse statique 10.42.xx.1
et qu’elle utilise le routeur 10.42.0.1 et serveur DNS 10.42.0.1.

Pour `/etc/network/interfaces` : 
```bash
iface enp0s3 inet static
      address 10.42.xx.1/16
      gateway 10.42.0.1
```

* Redémarre l'interface réseau : `ifup [nom de l'interface]`
* Vérifier l'adresse IP de la machine : `ip a show`
* Vérifier l’adresse du routeur avec la commande : `ip route show`
* Vérifier si la configuration DNS fonctionne correctement : `host www.univ-lille.fr`
* Redémarre la machine virtuelle pour vérifier la persistance de la configuration : `reboot`


# Configurer et mettre à jour la machine virtuelle

## Questions :

1. Quelle commande avez vous utilisée ? : 
2. Que se passe-t’il ? :
3. Pourquoi ? :

1. Quelle est la signification de l’option --login ? :
2. Pourquoi est-il intéressant de l’utiliser ? :

## Mise à jour:

Commande : `apt update && apt full-upgrade`
Redémarré la machine : `reboot`

## Installation : 

Commande : `apt install vim`
Commande : `apt install less`
Commande : `apt install tree`
Commande : `apt install rsync`

## Trucs en plus :

Se rendre sur la machine physique.  
Se rendre dans le fichier `$HOME/.ssh/config`.  

