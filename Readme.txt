# Projet 2 - *Nom de l'application ici*

**Nom de votre application** est une application Android qui permet à un utilisateur d'afficher sa chronologie Twitter et de publier un nouveau tweet. L'application utilise [Twitter REST API](https://dev.twitter.com/rest/public).

Temps passé : **X** heures passées au total

## Histoires d'utilisateurs

La fonctionnalité **obligatoire** suivante est terminée :

- [x ] L'utilisateur peut **composer et publier un nouveau tweet**
  - [x] L'utilisateur peut cliquer sur une icône "Composer" dans la barre d'action en haut à droite
  - [x] L'utilisateur peut alors saisir un nouveau tweet et le publier sur Twitter
  - [x] L'utilisateur est ramené à la chronologie d'accueil avec **nouveau tweet visible** dans la chronologie
  - [x] Le tweet nouvellement créé doit être inséré manuellement dans la chronologie et ne pas dépendre d'une actualisation complète
  - [x] L'utilisateur peut ** voir un compteur avec le nombre total de caractères restants pour le tweet ** sur la page de composition du tweet

Les fonctionnalités **facultatives** suivantes sont implémentées :

- [x] L'utilisateur utilise **les couleurs et les styles "de marque Twitter"**
- [x] L'utilisateur peut cliquer sur les liens dans les tweets lancer le navigateur Web
- [ ] L'utilisateur peut **sélectionner "répondre" dans la vue détaillée pour répondre à un tweet**
- [ ] L'action "Compose" est déplacée vers un FloatingActionButton au lieu de sur l'AppBar
- [ ] La fonctionnalité Composer un tweet est construite à l'aide d'une superposition modale
- [x] Utilisez Parcelable au lieu de Serializable à l'aide de la populaire [bibliothèque Parceler] (http://guides.codepath.org/android/Using-Parceler).
- [x] L'utilisateur peut ** ouvrir l'application Twitter hors ligne et voir les derniers tweets chargés **. Les tweets persistants dans SQLite sont actualisés à chaque lancement d'application. Alors que les "données en direct" sont affichées lorsque l'application peut les obtenir à partir de l'API Twitter, elles sont également enregistrées pour une utilisation en mode hors ligne.
- [x] Lorsqu'un utilisateur quitte la vue de composition sans publier et qu'il existe du texte, invite à enregistrer ou à supprimer le brouillon. S'il est enregistré, le brouillon doit alors être **persistant sur le disque** et peut ensuite être repris à partir de la vue de composition.
- [ ] Activez votre application pour recevoir des intentions implicites d'autres applications. Lorsqu'un lien est partagé à partir d'un navigateur Web, il doit pré-remplir le texte et le titre de la page Web lors de la rédaction d'un tweet.

Les fonctionnalités **supplémentaires** suivantes sont implémentées :

- [ ] Énumérez tout ce que vous pouvez faire pour améliorer la fonctionnalité de l'application !

## Procédure vidéo

Voici une présentation des user stories implémentées :

<img src='https://submissions.us-east-1.linodeobjects.com/android_university/vNuYXTht.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />


## Remarques

Décrivez les difficultés rencontrées lors de la création de l'application.

## Bibliothèques open-source utilisées

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Requêtes HTTP asynchrones simples avec analyse JSON
- [Glide](https://github.com/bumptech/glide) - Bibliothèque de chargement et de mise en cache d'images pour Android

## Licence

    Copyright [aaaa] [nom du titulaire des droits d'auteur]

    Sous licence Apache License, Version 2.0 (la "Licence");
    vous ne pouvez pas utiliser ce fichier sauf en conformité avec la licence.
    Vous pouvez obtenir une copie de la licence à

        http://www.apache.org/licenses/LICENSE-2.0

    Sauf si requis par la loi applicable ou convenu par écrit, le logiciel
    distribué sous la Licence est distribué sur une BASE "TEL QUEL",
    SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
    Consultez la licence pour connaître les autorisations spécifiques à la langue et
    limitations en vertu de la licence.























------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## User Stories

The following **required** functionality is completed:

- [ x] User can **sign in to Twitter** using OAuth login
- [ x]	User can **view tweets from their home timeline**
  - [ x] User is displayed the username, name, and body for each tweet
  - [ x] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
- [ x] User can refresh tweets timeline by pulling down to refresh

The following **optional** features are implemented:

- [ x] User can view more tweets as they scroll with infinite pagination
- [x ] Improve the user interface and theme the app to feel "twitter branded"
- [ x] Links in tweets are clickable and will launch the web browser
- [x ] User can tap a tweet to display a "detailed" view of that tweet
- [ ] User can see embedded image media within the tweet detail view
- [ ] User can watch embedded video within the tweet
- [ ] User can open the twitter app offline and see last loaded tweets
- [x ] On the Twitter timeline, leverage the CoordinatorLayout to apply scrolling behavior that hides / shows the toolbar.

The following **additional** features are implemented:

- [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://submissions.us-east-1.linodeobjects.com/android_university/PmQSz8wc.gif' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

## Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.