package org.example;

import java.text.DecimalFormat;

public class Mode {
    // Notes du manche sous forme de libellé
    // 0 : Do/C
    // 1 : Do#/C# ou Reb/Db
    // 2 : Re/D
    // 3 : Re#/D# ou Mib/Eb
    // 4 : Mi/E
    // 5 : Fa/F
    // 6 : Fa#/F# ou Solb/Gb
    // 7 : Sol/G
    // 8 : Sol#/G# ou Lab/Ab
    // 9 : La/A
    //10 : La#/A# ou Sib/Bb
    //11 : Si/B
    private String [][] sNote = new String[6][23];
    // Note du manche sous forme d'entier sur les 6 cordes de la guitare
    // 0 : Do - C
    // 1 : Do#
    // 2 : Re - D
    // 3 : Re#
    // 4 : Mi - E
    // 5 : Fa - F
    // 6 : Fa#
    // 7 : Sol - G
    // 8 : Sol #
    // 9 : La - A
    //10 : La #
    //11 : Si - B
    private int [][] iNote = new int[6][23];
    // Correspond au 7 notes possibles à partir d'une note dans la tonalité voulue
    private int [] iTonalite = new int[7];

    // -----------------------------------------------------------------
    // sGetNote
    //   Retourne la chaine de caractere correspondant à la note
    // INPUT:
    //   iNote : note sous forme d'entier correspondant à la note à transformer
    // OUTPUT:
    //   String : chaine de caractère correspondant au libelle de la note
    // -----------------------------------------------------------------
    public String sGetNote (int iNote)
    {
        String sNote = "";

        switch(iNote)
        {
            case 0 :
                sNote = "Do";
                break;
            case 1 :
                sNote = "Do#/Reb";
                break;
            case 2 :
                sNote = "Re";
                break;
            case 3 :
                sNote = "Re#/Mib";
                break;
            case 4 :
                sNote = "Mi";
                break;
            case 5 :
                sNote = "Fa";
                break;
            case 6 :
                sNote = "Fa#/Solb";
                break;
            case 7 :
                sNote = "Sol";
                break;
            case 8 :
                sNote = "Sol#/Lab";
                break;
            case 9 :
                sNote = "La";
                break;
            case 10 :
                sNote = "La#/Sib";
                break;
            case 11 :
                sNote = "Si";
                break;
            case -1 :
                sNote = "Pas de note trouvée !";
                break;
            default :
                System.out.println("Erreur Case avec iNote = "+ iNote);
                break;
        }
        return (sNote);
    }

    // -----------------------------------------------------------------
    // Mode
    //   Constructeur de la classe Mode. Initialisation du manche d'une guitare
    //   de 23 frettes par ses notes.
    // INPUT:
    //  -
    // VARIABLE:
    //   sNote est initialisé avec la chaine '-'
    //   iNote est initialisé avec la note sous forme d'entier. Sert de base pour les autres
    //   calcul afin de compléter sNote ensuite
    // OUTPUT:
    //   -
    // -----------------------------------------------------------------
    public Mode ()
    {
        int iLocalNote = 0;

        System.out.println("Constructeur Classe Mode. Initialisation du manche");
        // On initialise les 6 cordes
        for (int iLocalCorde = 0; iLocalCorde < 6; iLocalCorde++)
        {
            // Accordage standard
            if (iLocalCorde == 0) iLocalNote = 4; // MI
            if (iLocalCorde == 1) iLocalNote = 9; // LA
            if (iLocalCorde == 2) iLocalNote = 2; // RE
            if (iLocalCorde == 3) iLocalNote = 7; // SOL
            if (iLocalCorde == 4) iLocalNote = 11;// SI
            if (iLocalCorde == 5) iLocalNote = 4; // MI
            for (int iCase = 0; iCase < 23; iCase++)
            {
                // Initialise les notes à '-'
                sNote[iLocalCorde][iCase] = "-";
                // Initialise les notes sous la forme d'un entier
                iNote[iLocalCorde][iCase] = iLocalNote;
                iLocalNote++;
                if (iLocalNote > 11) {
                    iLocalNote = 0;
                }
            }
        }
    }

    // -----------------------------------------------------------------
    // SetMode
    //   Calcul et met à jour le mode en fonction d'une note
    // INPUT:
    //   iNote : note sous forme d'entier correspondant à la note à transformer
    //   iLiteMode : Interval entre 2 notes
    // VARIABLE:
    //   iTonalite : Calcul des notes appartenant à la tonalité
    // OUTPUT:
    //   -
    // -----------------------------------------------------------------
    private void SetMode (int iNote, int [] iListeMode)
    {
        // On recupere les notes de la gamme à partir de la tonalité
        for (int iMode = 0; iMode < 7; iMode++)
        {
            if (iMode == 0)
            {
                iTonalite[0] = iNote;
            }
            else
            {
                iTonalite[iMode] = iTonalite[iMode - 1] + iListeMode[iMode];
                if (iTonalite[iMode] > 11) {
                    iTonalite[iMode] = iTonalite[iMode] - 12;
                }
            }
            System.out.println("iTonalite :" + sGetNote(iTonalite[iMode]));
        }
        SetNoteModeManche();
    }

    // -----------------------------------------------------------------
    // ModeIonien
    //   Calcul le mode
    // INPUT:
    //   iNote est la note de référence du mode
    // VARIABLE:
    //   iTonalite : Calcul des notes appartenant à la tonalité
    //   iListeMode : liste des intervals entre 2 notes
    // OUPUT:
    //   -
    // -----------------------------------------------------------------
    public void ModeIonien (int iNote)
    {
        // Le mode Ionien est basé sur la gamme majeur naturelle
        int[] iListeMode = {0,2,2,1,2,2,2,1};

        System.out.println("Mode Ionien " + sGetNote (iNote) + " Majeur");
        SetMode(iNote, iListeMode);
    }

    // -----------------------------------------------------------------
    // ModeDorien
    //   Calcul le mode
    // INPUT:
    //   iNote est la note de référence du mode
    // VARIABLE:
    //   iTonalite : Calcul des notes appartenant à la tonalité
    //   iListeMode : liste des intervals entre 2 notes
    // OUPUT:
    //   -
    // -----------------------------------------------------------------
    public void ModeDorien (int iNote)
    {
        int[] iListeMode = {0,2,1,2,2,2,1,2};

        System.out.println("Mode Dorien " + sGetNote (iNote) + " Mineur");
        SetMode(iNote, iListeMode);
    }

    // -----------------------------------------------------------------
    // ModePhrygien
    //   Calcul le mode
    // INPUT:
    //   iNote est la note de référence du mode
    // VARIABLE:
    //   iTonalite : Calcul des notes appartenant à la tonalité
    //   iListeMode : liste des intervals entre 2 notes
    // OUPUT:
    //   -
    // -----------------------------------------------------------------
    public void ModePhrygien (int iNote)
    {
        int[] iListeMode = {0,1,2,2,2,1,2,2};

        System.out.println("Mode Phrygien " + sGetNote (iNote) + " Mineur");
        SetMode(iNote, iListeMode);
    }

    // -----------------------------------------------------------------
    // ModeLydien
    //   Calcul le mode
    // INPUT:
    //   iNote est la note de référence du mode
    // VARIABLE:
    //   iTonalite : Calcul des notes appartenant à la tonalité
    //   iListeMode : liste des intervals entre 2 notes
    // OUPUT:
    //   -
    // -----------------------------------------------------------------
    public void ModeLydien (int iNote)
    {
        // Le mode Ionien est basé sur la gamme majeur naturelle
        int[] iListeMode = {0,2,2,2,1,2,2,1};

        System.out.println("Mode Lydien " + sGetNote (iNote) + " Majeur");
        SetMode(iNote, iListeMode);
    }

    // -----------------------------------------------------------------
    // ModeMixolidien
    //   Calcul le mode
    // INPUT:
    //   iNote est la note de référence du mode
    // VARIABLE:
    //   iTonalite : Calcul des notes appartenant à la tonalité
    //   iListeMode : liste des intervals entre 2 notes
    // OUPUT:
    //   -
    // -----------------------------------------------------------------
    public void ModeMixolydien (int iNote)
    {
        // Le mode Ionien est basé sur la gamme majeur naturelle
        int[] iListeMode = {0,2,2,1,2,2,1,2};

        System.out.println("Mode Mixolydien " + sGetNote (iNote) + " Majeur");
        SetMode(iNote, iListeMode);
    }

    // -----------------------------------------------------------------
    // ModeAeolien
    //   Calcul le mode
    // INPUT:
    //   iNote est la note de référence du mode
    // VARIABLE:
    //   iTonalite : Calcul des notes appartenant à la tonalité
    //   iListeMode : liste des intervals entre 2 notes
    // OUPUT:
    //   -
    // -----------------------------------------------------------------
    public void ModeAeolien (int iNote)
    {
        int[] iListeMode = {0,2,1,2,2,1,2,2};

        System.out.println("Mode Aeolien " + sGetNote (iNote) + " Mineur");
        SetMode(iNote, iListeMode);
    }

    // -----------------------------------------------------------------
    // ModeLocrien
    //   Cacul l'ensemble des notes correspondant au mode
    //   à partir d'une note de référence.
    // INPUT:
    //   On donne la note de référence que l'on souhaite avoir.
    //   iListeMode : liste des intervals entre 2 notes
    // OUPUT:
    //   iTonalite : Mise à jour des notes dans la tonalité
    // -----------------------------------------------------------------
    public void ModeLocrien (int iNote)
    {
        int[] iListeMode = {0,1,2,2,1,2,2,2};

        System.out.println("Mode Locrien " + sGetNote (iNote) + " Mineur");
        SetMode(iNote, iListeMode);
    }

    // -----------------------------------------------------------------
    // ConvertNoteTone
    //   Recherche la note d'un mode pour l'afficher sous la forme, fondamentale, seconde, ...
    //   INPUT:
    //     iNote : Note à recherche dans le mode
    //     iTonalie : liste des note liées à la tonalité
    //   OUTPUT:
    //     Renvoie une chaine de caractere
    // -----------------------------------------------------------------
    private String ConvertNoteTone (int iNote)
    {
        String sNote = " ";
        // Scan si la note est dans le mode.
        for (int iMode = 0; iMode < 7; iMode++) {
            if (iNote == iTonalite[iMode]) {
                switch (iMode)
                {
                    case 0 :
                        // Fondamentale
                        sNote = "T";
                        break;
                    case 1 :
                        // Seconde
                        sNote = "2";
                        break;
                    case 2 :
                        // Tierce
                        sNote = "3";
                        break;
                    case 3 :
                        // Quarte
                        sNote = "4";
                        break;
                    case 4 :
                        // Quinte
                        sNote = "5";
                        break;
                    case 5 :
                        // Sixte
                        sNote = "6";
                        break;
                    case 6 :
                        // Septieme
                        sNote = "7";
                        break;
                    default:
                        sNote = " ";
                        break;
                }
            }
        }
        return sNote;
    }

    // -----------------------------------------------------------------
    // SetNoteModeManche
    //   Parcourt le manche afin de mettre à jour sNote en fonction des notes trouvée
    //  INPUT:
    //  VARIABLE:
    //    iNote : Numero des notes
    //    sNote : Note sur le manche
    //  OUTPUT :
    //    -
    // -----------------------------------------------------------------
    private void SetNoteModeManche ()
    {
        for (int iLocalCorde = 0; iLocalCorde < 6; iLocalCorde++)
        {
            for (int iCase = 0; iCase < 23; iCase++)
            {
                sNote[iLocalCorde][iCase] = ConvertNoteTone(iNote[iLocalCorde][iCase]);
            }
        }
    }

    // -----------------------------------------------------------------
    // PrintManche
    //   Affiche le manche de la guitare
    //  INPUT:
    //    -
    //  VARIABLE:
    //    sNote : Note sur le manche
    //  OUTPUT :
    //    -
    // -----------------------------------------------------------------
    public void PrintManche ()
    {
        DecimalFormat format = new DecimalFormat("00");
        for (int iCase = 0; iCase < 23; iCase++)
        {
            //System.out.println(format.format(iCase)+" "+sAccordManche[0][iCase]+sAccordManche[1][iCase]+sAccordManche[2][iCase]+sAccordManche[3][iCase]+sAccordManche[4][iCase]+sAccordManche[5][iCase]);
            System.out.println
                    (
                            format.format(iCase)+" -> "+
                                    sNote[0][iCase]+" "+
                                    sNote[1][iCase]+" "+
                                    sNote[2][iCase]+" "+
                                    sNote[3][iCase]+" "+
                                    sNote[4][iCase]+" "+
                                    sNote[5][iCase]
                    );
        }
    }
}
