package fr.univlille.donjon;

import fr.univlille.Mathf;

public enum Advice {
    CONSEIL_1("Les ennemis de l'étage des gardes ont une armure moyenne, si vous infligez peu de dégât, essayez d'utiliser vos sorts!"),
    CONSEIL_2("Le chien de garde ne possède pas de DEF, essaie de l'attaquer sans vos sorts!"),
    CONSEIL_3("ARS inflige certes beaucoup de dégâts, mais est assez fragile, tuez-le vite!"),
    CONSEIL_4("La Brave M est l'ennemi le plus dangereux du cachot"),
    CONSEIL_5("Les ennemis de la bibliothèque sont assez fragiles, mais faites  attention à leurs attaques!"),
    CONSEIL_6("Le professeur d'EPS est très résistant, mais il n'inflige que peu de dégâts, vous pouvez lancer en boucle vos sorts de soin pour vous soigner!"),
    CONSEIL_7("Le livre scolaire magic est l'ennemi qui inflige le plus de dégâts de la bibliothèque, mais il est très fragile!"),
    CONSEIL_8("Les ennemis de la ville en feu sont extrêmements résistants aux attaques de base, utilisez vos sorts!"),
    CONSEIL_9("Les Chaossos ne prendront presque aucun dégât des attaques de base, attaquez avec vos sort!"),
    CONSEIL_10("Seulement un seul monstre dans l'étage de la luxure infernale possède de l'armure, attaquez le donc avec vos attaques de base!"),
    CONSEIL_11("La Succubus est la seule à avoir de l'armure dans son étage!"),
    CONSEIL_12("Les attaques du Golem sont ridiculement faibles, mais il possède une quantité astronomique de HP!"),
    CONSEIL_13("Malgré le fait que Gardemin représente l'ordre, il ne suit que très peu les règles."),
    CONSEIL_14("Même si le chien de garde est dangereux, il est aussi très câlin!"),
    CONSEIL_15("Jean Merlin à beau être le chef de la grande bibliothèque, il est assez idiot."),
    CONSEIL_16("Des rumeurs circulent sur le fait que le livre de magie soit en réalité un Picsou magazine!"),
    CONSEIL_17("Personne ne sait ce que le Professeur d'EPS fait dans la bibliothèque."),
    CONSEIL_18("Les ficelles de l'araignée de la bibliothèque permettent de fabriquer des habits très confortables!"),
    CONSEIL_19("Anarchon est tout le temps énervé car il ne comprend pas pourquoi personne ne veut de lui en tant que chef de la tour, même si tout le monde sait qu'il ne saura jamais comment la diriger."),
    CONSEIL_20("Les robes jaunes sont très redoutées par le chef de la tour, il a peur qu'elles commencent à venir pour lui."),
    CONSEIL_21("Les Chaossos ne possèdent aucune intelligence, tout ce qu'ils veulent c'est harceler et suivre aveuglément des idiots."),
    CONSEIL_22("Lilithia ne possède aucun sens moral et aucune pudeur, elle serait même capable d'expliquer des connaissances impies et perverses à des enfants!"),
    CONSEIL_23("Les succubus sont aussi collantes car elles n'ont pas eu de relation amoureuse depuis des millénaires, elles ne demandent qu'un peu d'amour et d'attention."),
    CONSEIL_24("Les Playgirls suivent Lilithia car elle a posé pour la couverture d'un de leur magasine!"),
    CONSEIL_25("Le Golem ne possède aucune intelligence il ne sait que suivre et écouter aveuglément les femmes de l'étage infernal en échange d'attention de leur part."),
    CONSEIL_26("Jupiter n'est que le surnom que s'est donné l'archi roi démon, il est tellement imbu de lui-même qu'il pense être un dieu."),
    ;
    private String help;

    private Advice(String advice){
        this.help = advice;
    }

    public String getHelp(){
        return help;
    }

    public static Advice random(){
        return Mathf.random(values());
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + "[help:" + help + "]";
    }
}
