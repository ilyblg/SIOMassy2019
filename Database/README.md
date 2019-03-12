# Installer votre base de données #

__Ce qui suit introduit des manipulations SQL__

## Préparez-vous ##
Vous trouverez ici les fichiers :
 * agriotes2019.mwb : Le fichier Wokbench de la base
 * create-agriotes2019.sql : Créer la base vide
 * hydrate-agriotes2019.sql : Ajouter déclencheurs et données
 * reset-agriotes2019.sql : Effacer et réinjecter les données
 * schema-agriotes2019.png : Image du schéma de la base
 * triggers-agriotes2019.sql : Les déclencheurs
 
 ## Comment l'installer ? ##
 ### Etape 1 ###
 
 Il vous suffit d'ouvrir le fichier : **create-agriotes2019.sql**
 et d'exécuter le code SQL s'y trouvant, dans *Workbench* par exemple.
 
 ### Etape 2 ###
 
**Attention**
Veuillez avoir votre serveur démarré et d'y être connecté.
 
 Ouvrez le fichier : **hydrate-agriotes2019.sql**
 et exécuter le code SQL s'y trouvant sur la base de donnée précédement créée, dans *Workbench* par exemple.

 ## Purger la base de donnée  ##

 Ouvrez le fichier : **reset-agriotes2019.sql**
 et exécuter le code SQL s'y trouvant, dans *Workbench* par exemple.

Votre base sera réinitialisée avec les valeurs prédéfinis et prête à être utilisée.

-----
*Rédacteur du présent document, responsable github/ProjetE4: Miguel.M*
