package com.company;

import java.util.Random;

public class Main {

    public static int[] heroesHealth = {270, 280, 250, 99, 270, 130, 200, 500};
    public static String[] heroesNames = {"Lu Kang ", "Jax ",
            "Scorpion ", "Medic ", "Thor ", "Lucky ", "Berserk ", "Golem "};
    public static int[] heroesStrike = {20, 15, 25, 20, 7, 16, 1, 3};

    public static String bossName = "Shao Kahn ";
    public static int bossHealth = 700;
    public static int bossStrike = 50;
    public static String superStrike = "";
    public static int roundNumber = 0;

    public static void main(String[] args) {
        // write your code here
        printStatistics();
        System.out.println("------The game started-------");

        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        System.out.println("-----Round " + roundNumber + "-----");
        superStrike = getSuperStrikeHero();
        bossHits();
        heroesHits();
        printStatistics();
    }

    public static void golem() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (!(heroesNames[i].equals("Golem "))) {
                heroesHealth[i] = heroesHealth[i] + ((bossStrike / 5) * 1);
                System.out.println("Golem поглотил удар по игроку " + heroesNames[i]);
            } else {
                continue;
            }
        }
    }


    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!! " +
                    "Mortal Kombat finished");
            return true;
        }

        boolean allHeroesDead = true;

        for (int heroHealth : heroesHealth) {
            if (heroHealth > 0) {
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead) {
            System.out.println(bossName +
                    " Won!!! Mortal Combat finished");
        }
        return allHeroesDead;
    }

    public static void heroesHits() {
        Random random = new Random();
        int coeff = random.nextInt(9) + 2;
        for (int i = 0; i < heroesStrike.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (superStrike.equals(heroesNames[i])) {
                    bossHealth = bossHealth - heroesStrike[i] * coeff;
                    System.out.println("Super strike damage " +
                            superStrike + " " + (heroesStrike[i] * coeff));
                } else if (heroesNames[i].equals("Medic ")) {
                    int heroCoeff = getMedicStrike();
                    if (heroesHealth[heroCoeff] > 100) {
                        heroesHealth[heroCoeff] = heroesHealth[heroCoeff] + heroesStrike[3];
                        System.out.println(heroesNames[heroCoeff] + " have +" + heroesStrike[3] + " HP");
                    } else {
                        continue;
                    }
                } else if (heroesNames[i].equals("Lucky ")) {
                    int luckyRandom = random.nextInt(2);
                    if (luckyRandom == 1) {
                        bossHealth = bossHealth - heroesStrike[i];
                        heroesHealth[i] = heroesHealth[i] + bossStrike;
                        System.out.println("Lucky  уклонился от удара");
                    } else if (luckyRandom == 0) {
                        bossHealth = bossHealth - heroesStrike[i];
                    }
                } else if (heroesNames[i].equals("Berserk ")) {
                    int berserkRandom = random.nextInt(32);
                    heroesHealth[i] = heroesHealth[i] + berserkRandom;
                    bossHealth = bossHealth - (heroesStrike[i] + berserkRandom);
                    System.out.println("Berserk strike = " + (heroesStrike[i] + berserkRandom));
                } else if (heroesNames[i].equals("Golem ")) {
                    golem();
                    heroesHealth[i] = heroesHealth[i] - ((bossStrike / 5 * 1) * heroesNames.length);
                    bossHealth = bossHealth - heroesStrike[i];
                } else {
                    bossHealth = bossHealth - heroesStrike[i];
                }
            }
            if (bossHealth < 0) {
                bossHealth = 0;
            }
        }
    }

    public static void bossHits() {
        Random thorRandom = new Random();
        int thorSuperStrike = thorRandom.nextInt(2);
        for (int i = 0; i < heroesHealth.length; i++) {
            if (thorSuperStrike == 0) {
                if (heroesHealth[i] > 0 && bossHealth > 0
                        && thorSuperStrike == 0) {
                    heroesHealth[i] = heroesHealth[i] - bossStrike;
                }
                if (heroesHealth[i] < 0) {
                    heroesHealth[i] = 0;
                }
            } else if (thorSuperStrike == 1) {
                System.out.println("Thor оглушил босса");
                break;
            }
        }
    }

    public static String getSuperStrikeHero() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        boolean flag = true;
        while (flag) {
            if (!(heroesNames[randomIndex].equals("Medic "))) {
                flag = false;
            } else {
                randomIndex = random.nextInt(heroesNames.length);
            }
        }

        return heroesNames[randomIndex];
    }

    public static int getMedicStrike() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        boolean flag = true;
        while (flag) {
            if (!(heroesNames[randomIndex].equals("Medic "))) {
                flag = false;
            } else {
                randomIndex = random.nextInt(heroesNames.length);
            }
        }
        return randomIndex;
    }

    public static void printStatistics() {
        System.out.println("-----------------");
        for (int i = 0; i < heroesNames.length; i++) {
            System.out.println(heroesNames[i] + "= health " +
                    heroesHealth[i] + " strike [" +
                    heroesStrike[i] + "]");
        }
        System.out.println(bossName + "= health " + bossHealth +
                " strike [" + bossStrike + "]");
    }
}
