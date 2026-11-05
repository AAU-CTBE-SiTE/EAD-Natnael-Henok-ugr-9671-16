package com.craft;

/**
 * Runs every stage of the lab in the same order as the handout.
 * Each stage has its own package, Student class, App class and XML config
 * (src/main/resources/stageNN/), so every stage keeps working.
 */
public class RunAll {

    public static void main(String[] args) {
        String[] none = new String[0];

        header("Stage 1: Tight coupling");
        com.craft.stage01.App.main(none);

        header("Stage 2: Let Spring manage object creation");
        com.craft.stage02.App.main(none);

        header("Stage 3: Add fields, set them by hand");
        com.craft.stage03.App.main(none);

        header("Stage 4: Setter-based injection");
        com.craft.stage04.App.main(none);

        header("Stage 5: Constructor-based injection");
        com.craft.stage05.App.main(none);

        header("Stage 6: Literal dependency injection");
        com.craft.stage06.App.main(none);

        header("Stage 7: Object dependencies (ref)");
        com.craft.stage07.App.main(none);

        header("Stage 8: Object injection with a constructor");
        com.craft.stage08.App.main(none);

        header("Stage 9: Collections (list, array, set)");
        com.craft.stage09.App.main(none);

        header("Stage 10: Bean scopes");
        com.craft.stage10.App.main(none);
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
