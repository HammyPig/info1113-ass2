package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
import java.io.FileReader;
import java.util.Iterator;

public class App extends PApplet {

    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;

    private Player player;
    private int playerLives = 3;
    private Ghost ghost;
    private Cell[][] map;
    
    // Images
    private PImage[] playerSprites = new PImage[5];
    private PImage wallV;
    private PImage wallH;
    private PImage wallUL;
    private PImage wallUR;
    private PImage wallDL;
    private PImage wallDR;
    private PImage fruitSprite;
    private PImage ghostSprite;

    public App() {
        //Set up your objects
    }

    public void setup() {
        frameRate(3);

        // Player Sprites
        playerSprites[0] = loadImage("src/main/resources/playerUp.png");
        playerSprites[1] = loadImage("src/main/resources/playerLeft.png");
        playerSprites[2] = loadImage("src/main/resources/playerDown.png");
        playerSprites[3] = loadImage("src/main/resources/playerRight.png");
        playerSprites[4] = loadImage("src/main/resources/playerClosed.png");

        // Wall Sprites
        wallV = loadImage("src/main/resources/vertical.png");
        wallH = loadImage("src/main/resources/horizontal.png");
        wallUL = loadImage("src/main/resources/upLeft.png");
        wallUR = loadImage("src/main/resources/upRight.png");
        wallDL = loadImage("src/main/resources/downLeft.png");
        wallDR = loadImage("src/main/resources/downRight.png");
        fruitSprite = loadImage("src/main/resources/fruit.png");
        ghostSprite = loadImage("src/main/resources/ghost.png");

        String mapFile = parseConfig("config.json");
        map = parseMap(mapFile);
        player.setMap(map);
        ghost.setMap(map);
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void draw() { 

        player.tick(); // Update player
        ghost.tick();

        // Draw all objects
        background(0, 0, 0); // Refresh screen
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    map[i][j].draw(this);
                }
            }
        }

        playerLives = player.getLives();
        for (int i = 0; i < playerLives; i++) {
            image(playerSprites[3], 2 + 32 * i, 546);
        }
    }

    public String parseConfig(String filename) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONObject a = (JSONObject) obj;

            // temp
            String mapFile = (String) a.get("map");
            setPlayerLives((int) (long) a.get("lives"));
            int speed = (int) (long) a.get("speed");
            JSONArray modeLengths = (JSONArray) a.get("modeLengths");

            return mapFile;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cell[][] parseMap(String filename) {
        File f = new File(filename);

        try {
            // Find no. of rows and columns
            Scanner lenFinder = new Scanner(f);

            String firstRow = lenFinder.nextLine();
            int cols = firstRow.length();

            int rows = 1;
            while (lenFinder.hasNextLine()) {
                lenFinder.nextLine();
                rows++;
            }

            lenFinder.close();

            Cell[][] grid = new Cell[rows][cols]; // empty array with size of map rows and cols
            
            Scanner input = new Scanner(f);

            // Fill array with objects
            int i = 0;
            while (input.hasNextLine()) {
                String line = input.nextLine();

                for (int j = 0; j < line.length(); j++) {
                    char ch = line.charAt(j);

                    switch (ch) {
                        case '0':
                            break;
                        case '1':
                            grid[i][j] = new Wall(j, i, wallH);
                            break;
                        case '2':
                            grid[i][j] = new Wall(j, i, wallV);
                            break;
                        case '3':
                            grid[i][j] = new Wall(j, i, wallUL);
                            break;
                        case '4':
                            grid[i][j] = new Wall(j, i, wallUR);
                            break;
                        case '5':
                            grid[i][j] = new Wall(j, i, wallDL);
                            break;
                        case '6':
                            grid[i][j] = new Wall(j, i, wallDR);
                            break;
                        case '7':
                            grid[i][j] = new Fruit(j, i, fruitSprite);
                            break;
                        case 'p':
                            player = new Player(j, i, playerLives, playerSprites);
                            grid[i][j] = player;
                            break;
                        case 'g':
                            ghost = new Ghost(j, i, ghostSprite);
                            grid[i][j] = ghost;
                            break;
                    }

                }
                i += 1;
            }
            return grid;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setPlayerLives(int playerLives) {
        this.playerLives = playerLives;
    }

    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == UP) {
                player.setDir(0);
            }

            if (keyCode == LEFT) {
                player.setDir(1);
            }

            if (keyCode == DOWN) {
                player.setDir(2);
            }

            if (keyCode == RIGHT) {
                player.setDir(3);
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}
