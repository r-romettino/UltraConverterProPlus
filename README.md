# Projet de convertisseur d'unité

Ce projet est une application permettant la conversion d'unités (temps,
distances, températures)

L'application permet plusieurs modes de fonctionnement

### Trello : https://trello.com/b/42JZFEb6/ultraconverterproplus

### Auteurs : ROMETTINO Ryoma, BOUAZZATI Yassine, KARACA Melih, FAUCHEZ Mathéo

## Conversion simple

L'application peut executer des conversions 1 à 1 à la demande d'un
utilisateur, on choisit une unité dite d'entrée et une unité dite de sortie et
la valeur à convertir, puis affiche le résultat et l'ajoute à l'historique.

## Conversion multiple

Dans ce cas l'utilisateur fourni un document au format csv dans lequel les
données sont formattées comme suit :

```csv
Unité1,valeur_a_convertir,Unité2
```

Ce qui permet au programme de faire tout un tas de conversions différentes d'une
traite.
Le résultat sera un nouveau fichier au format csv que l'utilisateur devra
télécharger.
Il ressemblera a :

```csv
Unité1,valeur_a_convertir,Unité2,valeur_convertie
```

## Compréhension des erreurs

Nous avons ajouté un systeme comprennant les fautes
d'orthographe.
Par exemple si dans notre csv le programme trouve 'maitre' au lieu de 'Mètre'
alors le programme corrigera et continuera le processus.

# Roadmap

Nous avons prévu d'implementer de nouvelles fonctionnalités comme :

## Compréhension du langage humain

L'application devra comprendre une requete en langage humain comme :

```text
1 Miles en Mètres
```

Ce qui donnera le résultat comme dans une conversion simple (1600 Mètres ici)



## Calculs

L'application pourrait également permettre d'effectuer des calculs, si on
demande à convertir 1 Miles multipliés par 3 en Mètres alors le programme
renverra le résultat.
