package APBA;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainScreenController {

    File roster;
    Database mainData;
    Game newGame;

    private int NUM_SELECTED = 0;

    private boolean redsClicked = false;
    private boolean brewersClicked = false;
    private boolean piratesClicked = false;
    private boolean cubsClicked = false;
    private boolean cardinalsClicked = false;
    private boolean giantsClicked = false;
    private boolean padresClicked = false;
    private boolean dodgersClicked = false;
    private boolean rockiesClicked = false;
    private boolean dbacksClicked = false;
    private boolean bravesClicked = false;
    private boolean metsClicked = false;
    private boolean philliesClicked = false;
    private boolean marlinsClicked = false;
    private boolean nationalsClicked = false;

    private boolean redsoxClicked = false;
    private boolean yankeesClicked = false;
    private boolean bluejaysClicked = false;
    private boolean oriolesClicked = false;
    private boolean raysClicked = false;
    private boolean indiansClicked = false;
    private boolean tigersClicked = false;
    private boolean twinsClicked = false;
    private boolean royalsClicked = false;
    private boolean whitesoxClicked = false;
    private boolean asClicked = false;
    private boolean astrosClicked = false;
    private boolean rangersClicked = false;
    private boolean marinersClicked = false;
    private boolean angelsClicked = false;




    @FXML
    private static TableView rosterInfo;
    @FXML
    private static TableColumn<Player,String> name_col = new TableColumn<>("Name");
    @FXML
    private static TableColumn<Player,String> position_col = new TableColumn<>("Position");
    @FXML
    private static TableColumn<Player,String> pitching_hand_col = new TableColumn<>("Pitching Hand");
    @FXML
    private static TableColumn<Player,String> pitching_grade_col = new TableColumn<>("Pitching Grade");
    @FXML
    private static TableColumn<Player,String> bat_hand_col = new TableColumn<>("Batting Hand");
    @FXML
    private static TableColumn<Player,String> fielding_points_col = new TableColumn<>("Fielding Points");
    @FXML
    private static TableColumn<Player,String> pitching_rating_col = new TableColumn<>("Pitching Rating");
    @FXML
    private static TableColumn<Player,String> speed_col = new TableColumn<>("Speed");

    private static int TEAM_SELECTED = 0;
    private static int HOME_SELECTED = 0;
    private static int AWAY_SELECTED = 0;
    private int homeFieldingPoints = 0;
    private int awayFieldingPoints = 0;
    private int homeFieldingGrade = 0;
    private int awayFieldingGrade = 0;

    private ArrayList<Player> homeStartingLineup = new ArrayList<>();
    private ArrayList<Player> awayStartingLineup = new ArrayList<>();

    private ArrayList<Player> homeBattingOrder = new ArrayList<>(9);
    private ArrayList<Player> awayBattingOrder = new ArrayList<>(9);

    private final int MARINERS = 1;
    private final int ASTROS = 2;
    private final int ANGELS = 3;
    private final int ATHLETICS = 4;
    private final int RANGERS = 5;
    private final int INDIANS = 6;
    private final int TIGERS = 7;
    private final int TWINS = 8;
    private final int ROYALS = 9;
    private final int WHITESOX = 10;
    private final int REDSOX = 11;
    private final int YANKEES = 12;
    private final int RAYS = 13;
    private final int BLUEJAYS = 14;
    private final int ORIOLES = 15;
    private final int BRAVES = 16;
    private final int NATIONALS = 17;
    private final int METS = 18;
    private final int PHILLIES = 19;
    private final int MARLINS = 20;
    private final int BREWERS = 21;
    private final int CUBS = 22;
    private final int CARDINALS = 23;
    private final int PIRATES = 24;
    private final int REDS = 25;
    private final int DBACKS = 26;
    private final int ROCKIES = 27;
    private final int DODGERS = 28;
    private final int GIANTS = 29;
    private final int PADRES = 30;
    private int teamRightClicked = 0;

    private static String[] teams = {"ERROR", "Seattle Mariners", "Houston Astros", "Los Angeles Angels", "Oakland Athletics",
            "Texas Rangers", "Cleveland Indians", "Detroit Tigers", "Minnesota Twins","Kansas City Royals",
            "Chicago White Sox", "Boston Red Sox", "New York Yankees","Tampa Bay Rays", "Toronto Blue Jays",
            "Baltimore Orioles", "Atlanta Braves","Washington Nationals", "New York Mets", "Philadelphia Phillies",
            "Miami Marlins","Milwaukee Brewers", "Chicago Cubs", "St. Louis Cardinals", "Pittsburgh Pirates",
            "Cincinnati Reds", "Arizona Diamondbacks", "Colorado Rockies", "Los Angeles Dodgers","San Francisco Giants",
            "San Diego Padres"};
    private String pitchingGradeHome;
    private String pitchingGradeAway;
    private String pitchingRatingAway;
    private String pitchingRatingHome;

    private Stage rosterWindow;
    private Stage playerCard = new Stage();

    @FXML
    private Button start;
    @FXML
    private Button exit;
    @FXML
    private Button backToMain;
    @FXML
    private Button backToTeamSelect;
    @FXML
    private Button setLineup1;
    @FXML
    private Button setLineup2;
    @FXML
    private Button setBattingOrderHome;
    @FXML
    private Button setBattingOrderAway;
    @FXML
    private Button quitToTeamSelect;

    @FXML
    private Label eastA;
    @FXML
    private Label westA;
    @FXML
    private Label centralA;
    @FXML
    private Label eastN;
    @FXML
    private Label westN;
    @FXML
    private Label centralN;
    @FXML
    private Label home;
    @FXML
    private Label away;
    @FXML
    private Label lineup;
    @FXML
    private Label awayTeamLabel;
    @FXML
    private Label homeTeamLabel;
    @FXML
    private Label choose;
    @FXML
    private Label homeTeamBattingOrderLabel;
    @FXML
    private Label awayTeamBattingOrderLabel;
    @FXML
    private Label play;
    @FXML
    private Label ball;
    @FXML
    private Label rightClickInfo;

    @FXML
    private TextField player0;
    @FXML
    private TextField player1;
    @FXML
    private TextField player2;
    @FXML
    private TextField player3;
    @FXML
    private TextField player4;
    @FXML
    private TextField player5;
    @FXML
    private TextField player6;
    @FXML
    private TextField player7;
    @FXML
    private TextField player8;
    @FXML
    private TextField player0Order;
    @FXML
    private TextField player1Order;
    @FXML
    private TextField player2Order;
    @FXML
    private TextField player3Order;
    @FXML
    private TextField player4Order;
    @FXML
    private TextField player5Order;
    @FXML
    private TextField player6Order;
    @FXML
    private TextField player7Order;
    @FXML
    private TextField player8Order;

    @FXML
    private ChoiceBox catcher;
    @FXML
    private ChoiceBox pitcher;
    @FXML
    private ChoiceBox firstBase;
    @FXML
    private ChoiceBox secondBase;
    @FXML
    private ChoiceBox thirdBase;
    @FXML
    private ChoiceBox shortstop;
    @FXML
    private ChoiceBox leftField;
    @FXML
    private ChoiceBox rightField;
    @FXML
    private ChoiceBox centerField;


    @FXML
    private ImageView americanLeagueLogo;
    @FXML
    private ImageView nationalLeagueLogo;
    @FXML
    private ImageView apbaLogo;
    @FXML
    private ImageView firstBaseDot;
    @FXML
    private ImageView secondBaseDot;
    @FXML
    private ImageView thirdBaseDot;

    @FXML
    private Button rays;
    @FXML
    private Button bluejays;
    @FXML
    private Button yankees;
    @FXML
    private Button redsox;
    @FXML
    private Button orioles;
    @FXML
    private Button indians;
    @FXML
    private Button tigers;
    @FXML
    private Button twins;
    @FXML
    private Button royals;
    @FXML
    private Button whitesox;
    @FXML
    private Button as;
    @FXML
    private Button angels;
    @FXML
    private Button astros;
    @FXML
    private Button rangers;
    @FXML
    private Button mariners;
    @FXML
    private Button marlins;
    @FXML
    private Button braves;
    @FXML
    private Button padres;
    @FXML
    private Button reds;
    @FXML
    private Button phillies;
    @FXML
    private Button brewers;
    @FXML
    private Button cubs;
    @FXML
    private Button pirates;
    @FXML
    private Button cardinals;
    @FXML
    private Button giants;
    @FXML
    private Button rockies;
    @FXML
    private Button dbacks;
    @FXML
    private Button dodgers;
    @FXML
    private Button mets;
    @FXML
    private Button nationals;


    private Image angelsLogo = new Image(getClass().getResourceAsStream("Logos/angelslogo.png"));
    private Image asLogo = new Image(getClass().getResourceAsStream("Logos/aslogo.png"));
    private Image astrosLogo = new Image(getClass().getResourceAsStream("Logos/astroslogo.png"));
    private Image bravesLogo = new Image(getClass().getResourceAsStream("Logos/braveslogo.png"));
    private Image brewerLogo = new Image(getClass().getResourceAsStream("Logos/brewerslogo.png"));
    private Image cardinalsLogo = new Image(getClass().getResourceAsStream("Logos/cardinalslogo.png"));
    private Image cubsLogo = new Image(getClass().getResourceAsStream("Logos/cubslogo.png"));
    private Image dbacksLogo = new Image(getClass().getResourceAsStream("Logos/dbackslogo.png"));
    private Image dodgersLogo = new Image(getClass().getResourceAsStream("Logos/dodgerslogo.png"));
    private Image giantsLogo = new Image(getClass().getResourceAsStream("Logos/giantslogo.png"));
    private Image indiansLogo = new Image(getClass().getResourceAsStream("Logos/indianslogo.png"));
    private Image jaysLogo = new Image(getClass().getResourceAsStream("Logos/jayslogo.png"));
    private Image marinersLogo = new Image(getClass().getResourceAsStream("Logos/marinerslogo.png"));
    private Image marlinsLogo = new Image(getClass().getResourceAsStream("Logos/marlinslogo.png"));
    private Image metsLogo = new Image(getClass().getResourceAsStream("Logos/metslogo.png"));
    private Image nationalsLogo = new Image(getClass().getResourceAsStream("Logos/nationalslogo.png"));
    private Image oriolesLogo = new Image(getClass().getResourceAsStream("Logos/orioleslogo.png"));
    private Image padresLogo = new Image(getClass().getResourceAsStream("Logos/padreslogo.png"));
    private Image philliesLogo = new Image(getClass().getResourceAsStream("Logos/phillieslogo.png"));
    private Image piratesLogo = new Image(getClass().getResourceAsStream("Logos/pirateslogo.png"));
    private Image rangersLogo = new Image(getClass().getResourceAsStream("Logos/rangerslogo.png"));
    private Image raysLogo = new Image(getClass().getResourceAsStream("Logos/rayslogo.png"));
    private Image redsLogo = new Image(getClass().getResourceAsStream("Logos/redslogo.png"));
    private Image redsoxLogo = new Image(getClass().getResourceAsStream("Logos/redsoxlogo.png"));
    private Image rockiesLogo = new Image(getClass().getResourceAsStream("Logos/rockieslogo.png"));
    private Image royalsLogo = new Image(getClass().getResourceAsStream("Logos/royalslogo.png"));
    private Image tigersLogo = new Image(getClass().getResourceAsStream("Logos/tigerslogo.png"));
    private Image twinsLogo = new Image(getClass().getResourceAsStream("Logos/twinslogo.png"));
    private Image whitesoxLogo = new Image(getClass().getResourceAsStream("Logos/whitesoxlogo.png"));
    private Image yankeesLogo = new Image(getClass().getResourceAsStream("Logos/yankeeslogo.png"));
    private Image APBALogo = new Image(getClass().getResourceAsStream("Logos/apbalogo.png"));

    private Image homeImage;
    private Image awayImage;



    @FXML
    private void importRoster() {
        boolean validRosterFile = false;

        mainData = new Database();

        roster = new File(System.getProperty("user.dir") + "\\src\\APBA\\Rosters\\2018.csv");
        //roster = new File(System.getProperty("user.dir") + "\\2018.csv");

        try {
            while(!validRosterFile) {
                Scanner scan = new Scanner(roster);
                String header = scan.nextLine();
                if(header.equalsIgnoreCase("Name,Position,Team,Pitch Hand,Pitch Grade,Bats," +
                        "Fielding Points,Pitching Rating,Speed,11,12,13,14,15,16,21,22,23,24,25,26,31,32,33,34,35,36," +
                        "41,42,43,44,45,46,51,52,53,54,55,56,61,62,63,64,65,66,11_2,12_2,13_2,14_2,15_2,16_2,21_2," +
                        "22_2,23_2,24_2,25_2,26_2,31_2,32_2,33_2,34_2,35_2,36_2,41_2,42_2,43_2,44_2,45_2,46_2,51_2," +
                        "52_2,53_2,54_2,55_2,56_2,61_2,62_2,63_2,64_2,65_2,66_2") ||
                        header.equalsIgnoreCase("Name,Position,Team,Pitch Hand,Pitch Grade,Bats," +
                        "Fielding Points,Pitching Rating,Speed,11,12,13,14,15,16,21,22,23,24,25,26,31,32,33,34,35,36," +
                        "41,42,43,44,45,46,51,52,53,54,55,56,61,62,63,64,65,66,11_2,12_2,13_2,14_2,15_2,16_2,21_2," +
                        "22_2,23_2,24_2,25_2,26_2,31_2,32_2,33_2,34_2,35_2,36_2,41_2,42_2,43_2,44_2,45_2,46_2,51_2," +
                        "52_2,53_2,54_2,55_2,56_2,61_2,62_2,63_2,64_2,65_2,66_2,")) {
                    validRosterFile = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Please choose a valid Database File.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                mainData.fill(roster);
            }

            setButtonGraphics();
            initAmericanButtons();
            initNationalButtons();
            initRightClickNational();
            initRightClickAmerican();
            teamSelectScreen();

        } catch (FileNotFoundException ex) {
            System.err.print(ex.getMessage());
        } catch (NullPointerException ex) {
            //do nothing. this shouldn't happen
        }
    }

    @FXML
    private void exit() {
        System.exit(0);
    }

    private void setButtonGraphics() {
        rays.setGraphic(new ImageView(raysLogo));
        bluejays.setGraphic(new ImageView(jaysLogo));
        redsox.setGraphic(new ImageView(redsoxLogo));
        yankees.setGraphic(new ImageView(yankeesLogo));
        orioles.setGraphic(new ImageView(oriolesLogo));
        indians.setGraphic(new ImageView(indiansLogo));
        tigers.setGraphic(new ImageView(tigersLogo));
        royals.setGraphic(new ImageView(royalsLogo));
        twins.setGraphic(new ImageView(twinsLogo));
        whitesox.setGraphic(new ImageView(whitesoxLogo));
        as.setGraphic(new ImageView(asLogo));
        astros.setGraphic(new ImageView(astrosLogo));
        rangers.setGraphic(new ImageView(rangersLogo));
        mariners.setGraphic(new ImageView(marinersLogo));
        angels.setGraphic(new ImageView(angelsLogo));
        braves.setGraphic(new ImageView(bravesLogo));
        marlins.setGraphic(new ImageView(marlinsLogo));
        phillies.setGraphic(new ImageView(philliesLogo));
        mets.setGraphic(new ImageView(metsLogo));
        nationals.setGraphic(new ImageView(nationalsLogo));
        padres.setGraphic(new ImageView(padresLogo));
        giants.setGraphic(new ImageView(giantsLogo));
        rockies.setGraphic(new ImageView(rockiesLogo));
        dodgers.setGraphic(new ImageView(dodgersLogo));
        dbacks.setGraphic(new ImageView(dbacksLogo));
        reds.setGraphic(new ImageView(redsLogo));
        brewers.setGraphic(new ImageView(brewerLogo));
        cubs.setGraphic(new ImageView(cubsLogo));
        pirates.setGraphic(new ImageView(piratesLogo));
        cardinals.setGraphic(new ImageView(cardinalsLogo));
    }

    private void initAmericanButtons() {
        backToMain.setOnAction(e -> {
            allButtonsInvisible();
            mainScreen();
            choose.setVisible(false);
            awayTeamLabel.setVisible(false);
            homeTeamLabel.setVisible(false);
        });
        quitToTeamSelect.setOnAction(e -> {
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(null, "Are You Sure You Want to Quit?",
                    "Are You Sure?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            if(n == 0) {
                //END GAME
                newGame.quitGame();
                quitToTeamSelect.setVisible(false);
                teamSelectScreen();
                NUM_SELECTED = 0;
                home.setVisible(false);
                away.setVisible(false);
                lineup.setVisible(false);
                homeTeamLabel.setVisible(true);
                choose.setVisible(true);
                setBattingOrderAway.setVisible(false);
                setBattingOrderHome.setVisible(false);
                awayTeamBattingOrderLabel.setVisible(false);
                homeTeamBattingOrderLabel.setVisible(false);
                awayFieldingPoints = 0;
                homeFieldingPoints = 0;
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                checkNumSelected();
                allButtonsOpaque();
                resetLineups();
            }
        });
        backToTeamSelect.setOnAction(e -> {
            teamSelectScreen();
            NUM_SELECTED = 0;
            home.setVisible(false);
            away.setVisible(false);
            lineup.setVisible(false);
            homeTeamLabel.setVisible(true);
            choose.setVisible(true);
            setBattingOrderAway.setVisible(false);
            setBattingOrderHome.setVisible(false);
            awayTeamBattingOrderLabel.setVisible(false);
            homeTeamBattingOrderLabel.setVisible(false);
            awayFieldingPoints = 0;
            homeFieldingPoints = 0;
            checkNumSelected();
            allButtonsOpaque();
            resetLineups();
            setBattingOrderTextFieldsInvisible();
        });
        rays.setOnAction(e -> {
            if(raysClicked) {
                rays.setOpacity(1);
                raysClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                rays.setOpacity(0.4);
                raysClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = RAYS;
                    homeImage = raysLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = RAYS;
                    awayImage = raysLogo;
                }
                checkNumSelected();
            }
        });
        bluejays.setOnAction(e -> {
            if(bluejaysClicked) {
                bluejays.setOpacity(1);
                bluejaysClicked = false;
                NUM_SELECTED--;
                AWAY_SELECTED = 0;
                HOME_SELECTED = 0;
                checkNumSelected();
            } else {
                bluejays.setOpacity(0.4);
                bluejaysClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = BLUEJAYS;
                    homeImage = jaysLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = BLUEJAYS;
                    awayImage = jaysLogo;
                }
                checkNumSelected();
            }
        });
        yankees.setOnAction(e -> {
            if(yankeesClicked) {
                yankees.setOpacity(1);
                yankeesClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                yankees.setOpacity(0.4);
                yankeesClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = YANKEES;
                    homeImage = yankeesLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = YANKEES;
                    awayImage = yankeesLogo;
                }
                checkNumSelected();
            }
        });
        redsox.setOnAction(e -> {
            if(redsoxClicked) {
                redsox.setOpacity(1);
                redsoxClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                redsox.setOpacity(0.4);
                redsoxClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = REDSOX;
                    homeImage = redsoxLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = REDSOX;
                    awayImage = redsoxLogo;
                }
                checkNumSelected();
            }
        });
        orioles.setOnAction(e -> {
            if(oriolesClicked) {
                orioles.setOpacity(1);
                oriolesClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                orioles.setOpacity(0.4);
                oriolesClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = ORIOLES;
                    homeImage = oriolesLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = ORIOLES;
                    awayImage = oriolesLogo;
                }
                checkNumSelected();
            }
        });
        indians.setOnAction(e -> {
            if(indiansClicked) {
                indians.setOpacity(1);
                indiansClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                indians.setOpacity(0.4);
                indiansClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = INDIANS;
                    homeImage = indiansLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = INDIANS;
                    awayImage = indiansLogo;
                }
                checkNumSelected();
            }
        });
        tigers.setOnAction(e -> {
            if(tigersClicked) {
                tigers.setOpacity(1);
                tigersClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                tigers.setOpacity(0.4);
                tigersClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = TIGERS;
                    homeImage = tigersLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = TIGERS;
                    awayImage = tigersLogo;
                }
                checkNumSelected();
            }
        });
        whitesox.setOnAction(e -> {
            if(whitesoxClicked) {
                whitesox.setOpacity(1);
                whitesoxClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                whitesox.setOpacity(0.4);
                whitesoxClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = WHITESOX;
                    homeImage = whitesoxLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = WHITESOX;
                    awayImage = whitesoxLogo;
                }
                checkNumSelected();
            }
        });
        royals.setOnAction(e -> {
            if(royalsClicked) {
                royals.setOpacity(1);
                royalsClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                royals.setOpacity(0.4);
                royalsClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = ROYALS;
                    homeImage = royalsLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = ROYALS;
                    awayImage = royalsLogo;
                }
                checkNumSelected();
            }
        });
        twins.setOnAction(e -> {
            if(twinsClicked) {
                twins.setOpacity(1);
                twinsClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                twins.setOpacity(0.4);
                twinsClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = TWINS;
                    homeImage = twinsLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = TWINS;
                    awayImage = twinsLogo;
                }
                checkNumSelected();
            }
        });
        as.setOnAction(e -> {
            if(asClicked) {
                as.setOpacity(1);
                asClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                as.setOpacity(0.4);
                asClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = ATHLETICS;
                    homeImage = asLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = ATHLETICS;
                    awayImage = asLogo;
                }
                checkNumSelected();
            }
        });
        astros.setOnAction(e -> {
            if(astrosClicked) {
                astros.setOpacity(1);
                astrosClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                astros.setOpacity(0.4);
                astrosClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = ASTROS;
                    homeImage = astrosLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = ASTROS;
                    awayImage = astrosLogo;
                }
                checkNumSelected();
            }
        });
        angels.setOnAction(e -> {
            if(angelsClicked) {
                angels.setOpacity(1);
                angelsClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                angels.setOpacity(0.4);
                angelsClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = ANGELS;
                    homeImage = angelsLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = ANGELS;
                    awayImage = angelsLogo;
                }
                checkNumSelected();
            }
        });
        mariners.setOnAction(e -> {
            if(marinersClicked) {
                mariners.setOpacity(1);
                marinersClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                mariners.setOpacity(0.4);
                marinersClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = MARINERS;
                    homeImage = marinersLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = MARINERS;
                    awayImage = marinersLogo;
                }
                checkNumSelected();
            }
        });
        rangers.setOnAction(e -> {
            if(rangersClicked) {
                rangers.setOpacity(1);
                rangersClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                rangers.setOpacity(0.4);
                rangersClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = RANGERS;
                    homeImage = rangersLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = RANGERS;
                    awayImage = rangersLogo;
                }
                checkNumSelected();
            }
        });
    }

    private void initNationalButtons() {
        reds.setOnAction(e -> {
            if(redsClicked) {
                reds.setOpacity(1);
                redsClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                reds.setOpacity(0.4);
                redsClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = REDS;
                    homeImage = redsLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = REDS;
                    awayImage = redsLogo;
                }
                checkNumSelected();
            }
        });
        braves.setOnAction(e -> {
            if(bravesClicked) {
                braves.setOpacity(1);
                bravesClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                braves.setOpacity(0.4);
                bravesClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = BRAVES;
                    homeImage = bravesLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = BRAVES;
                    awayImage = bravesLogo;
                }
                checkNumSelected();
            }
        });
        padres.setOnAction(e -> {
            if(padresClicked) {
                padres.setOpacity(1);
                padresClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                padres.setOpacity(0.4);
                padresClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = PADRES;
                    homeImage = padresLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = PADRES;
                    awayImage = padresLogo;
                }
                checkNumSelected();
            }
        });
        phillies.setOnAction(e -> {
            if(philliesClicked) {
                phillies.setOpacity(1);
                philliesClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                phillies.setOpacity(0.4);
                philliesClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = PHILLIES;
                    homeImage = philliesLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = PHILLIES;
                    awayImage = philliesLogo;
                }
                checkNumSelected();
            }
        });
        marlins.setOnAction(e -> {
            if(marlinsClicked) {
                marlins.setOpacity(1);
                marlinsClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                marlins.setOpacity(0.4);
                marlinsClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = MARLINS;
                    homeImage = marlinsLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = MARLINS;
                    awayImage = marlinsLogo;
                }
                checkNumSelected();
            }
        });
        mets.setOnAction(e -> {
            if(metsClicked) {
                mets.setOpacity(1);
                metsClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                mets.setOpacity(0.4);
                metsClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = METS;
                    homeImage = metsLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = METS;
                    awayImage = metsLogo;
                }
                checkNumSelected();
            }
        });
        nationals.setOnAction(e -> {
            if(nationalsClicked) {
                nationals.setOpacity(1);
                nationalsClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                nationals.setOpacity(0.4);
                nationalsClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = NATIONALS;
                    homeImage = nationalsLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = NATIONALS;
                    awayImage = nationalsLogo;
                }
                checkNumSelected();
            }
        });
        brewers.setOnAction(e -> {
            if(brewersClicked) {
                brewers.setOpacity(1);
                brewersClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                brewers.setOpacity(0.4);
                brewersClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = BREWERS;
                    homeImage = brewerLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = BREWERS;
                    awayImage = brewerLogo;
                }
                checkNumSelected();
            }
        });
        cubs.setOnAction(e -> {
            if(cubsClicked) {
                cubs.setOpacity(1);
                cubsClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                cubs.setOpacity(0.4);
                cubsClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = CUBS;
                    homeImage = cubsLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = CUBS;
                    awayImage = cubsLogo;
                }
                checkNumSelected();
            }
        });
        pirates.setOnAction(e -> {
            if(piratesClicked) {
                pirates.setOpacity(1);
                piratesClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                pirates.setOpacity(0.4);
                piratesClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = PIRATES;
                    homeImage = piratesLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = PIRATES;
                    awayImage = piratesLogo;
                }
                checkNumSelected();
            }
        });
        cardinals.setOnAction(e -> {
            if(cardinalsClicked) {
                cardinals.setOpacity(1);
                cardinalsClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                cardinals.setOpacity(0.4);
                cardinalsClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = CARDINALS;
                    homeImage = cardinalsLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = CARDINALS;
                    awayImage = cardinalsLogo;
                }
                checkNumSelected();
            }
        });
        giants.setOnAction(e -> {
            if(giantsClicked) {
                giants.setOpacity(1);
                giantsClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                giants.setOpacity(0.4);
                giantsClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = GIANTS;
                    homeImage = giantsLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = GIANTS;
                    awayImage = giantsLogo;
                }
                checkNumSelected();
            }
        });
        rockies.setOnAction(e -> {
            if(rockiesClicked) {
                rockies.setOpacity(1);
                rockiesClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                rockies.setOpacity(0.4);
                rockiesClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = ROCKIES;
                    homeImage = rockiesLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = ROCKIES;
                    awayImage = rockiesLogo;
                }
                checkNumSelected();
            }
        });
        dbacks.setOnAction(e -> {
            if(dbacksClicked) {
                dbacks.setOpacity(1);
                dbacksClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                dbacks.setOpacity(0.4);
                dbacksClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = DBACKS;
                    homeImage = dbacksLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = DBACKS;
                    awayImage = dbacksLogo;
                }
                checkNumSelected();
            }
        });
        dodgers.setOnAction(e -> {
            if(dodgersClicked) {
                dodgers.setOpacity(1);
                dodgersClicked = false;
                NUM_SELECTED--;
                HOME_SELECTED = 0;
                AWAY_SELECTED = 0;
                checkNumSelected();
            } else {
                dodgers.setOpacity(0.4);
                dodgersClicked = true;
                NUM_SELECTED++;
                if(NUM_SELECTED == 1) {
                    HOME_SELECTED = DODGERS;
                    homeImage = dodgersLogo;
                } else if(NUM_SELECTED == 2) {
                    AWAY_SELECTED = DODGERS;
                    awayImage = dodgersLogo;
                }
                checkNumSelected();
            }
        });
    }

    private void initRightClickNational() {
        dodgers.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = DODGERS;
                displayRoster();
            }
        });
        rockies.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = ROCKIES;
                displayRoster();
            }
        });
        giants.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = GIANTS;
                displayRoster();
            }
        });
        padres.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = PADRES;
                displayRoster();
            }
        });
        dbacks.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = DBACKS;
                displayRoster();
            }
        });
        brewers.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = BREWERS;
                displayRoster();
            }
        });
        reds.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = REDS;
                displayRoster();
            }
        });
        cubs.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = CUBS;
                displayRoster();
            }
        });
        pirates.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = PIRATES;
                displayRoster();
            }
        });
        cardinals.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = CARDINALS;
                displayRoster();
            }
        });
        braves.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = BRAVES;
                displayRoster();
            }
        });
        mets.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = METS;
                displayRoster();
            }
        });
        nationals.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = NATIONALS;
                displayRoster();
            }
        });
        phillies.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = PHILLIES;
                displayRoster();
            }
        });
        marlins.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = MARLINS;
                displayRoster();
            }
        });
    }

    private void initRightClickAmerican() {
        mariners.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = MARINERS;
                displayRoster();
            }
        });
        astros.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = ASTROS;
                displayRoster();
            }
        });
        angels.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = ANGELS;
                displayRoster();
            }
        });
        as.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = ATHLETICS;
                displayRoster();
            }
        });
        rangers.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = RANGERS;
                displayRoster();
            }
        });
        indians.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = INDIANS;
                displayRoster();
            }
        });
        tigers.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = TIGERS;
                displayRoster();
            }
        });
        twins.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = TWINS;
                displayRoster();
            }
        });
        royals.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = ROYALS;
                displayRoster();
            }
        });
        whitesox.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = WHITESOX;
                displayRoster();
            }
        });
        bluejays.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = BLUEJAYS;
                displayRoster();
            }
        });
        redsox.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = REDSOX;
                displayRoster();
            }
        });
        yankees.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = YANKEES;
                displayRoster();
            }
        });
        orioles.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = ORIOLES;
                displayRoster();
            }
        });
        rays.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                TEAM_SELECTED = RAYS;
                displayRoster();
            }
        });
    }

    private void displayRoster() {
        showStage();
        switch (TEAM_SELECTED) {
            case BREWERS:
                rosterInfo.setItems(mainData.getMilwaukee());
                rosterWindow.getIcons().add(brewerLogo);
                teamRightClicked = BREWERS;
                break;
            case CUBS:
                rosterInfo.setItems(mainData.getChicagoCubs());
                rosterWindow.getIcons().add(cubsLogo);
                teamRightClicked = CUBS;
                break;
            case CARDINALS:
                rosterInfo.setItems(mainData.getCardinals());
                rosterWindow.getIcons().add(cardinalsLogo);
                teamRightClicked = CARDINALS;
                break;
            case PIRATES:
                rosterInfo.setItems(mainData.getPirates());
                rosterWindow.getIcons().add(piratesLogo);
                teamRightClicked = PIRATES;
                break;
            case REDS:
                rosterInfo.setItems(mainData.getReds());
                rosterWindow.getIcons().add(redsLogo);
                teamRightClicked = REDS;
                break;
            case BRAVES:
                rosterInfo.setItems(mainData.getBraves());
                rosterWindow.getIcons().add(bravesLogo);
                teamRightClicked = BRAVES;
                break;
            case PHILLIES:
                rosterInfo.setItems(mainData.getPhillies());
                rosterWindow.getIcons().add(philliesLogo);
                teamRightClicked = PHILLIES;
                break;
            case METS:
                rosterInfo.setItems(mainData.getMets());
                rosterWindow.getIcons().add(metsLogo);
                teamRightClicked = METS;
                break;
            case NATIONALS:
                rosterInfo.setItems(mainData.getNationals());
                rosterWindow.getIcons().add(nationalsLogo);
                teamRightClicked = NATIONALS;
                break;
            case MARLINS:
                rosterInfo.setItems(mainData.getMarlins());
                rosterWindow.getIcons().add(marlinsLogo);
                teamRightClicked = MARLINS;
                break;
            case ROCKIES:
                rosterInfo.setItems(mainData.getRockies());
                rosterWindow.getIcons().add(rockiesLogo);
                teamRightClicked = ROCKIES;
                break;
            case PADRES:
                rosterInfo.setItems(mainData.getPadres());
                rosterWindow.getIcons().add(padresLogo);
                teamRightClicked = PADRES;
                break;
            case DODGERS:
                rosterInfo.setItems(mainData.getDodgers());
                rosterWindow.getIcons().add(dodgersLogo);
                teamRightClicked = DODGERS;
                break;
            case DBACKS:
                rosterInfo.setItems(mainData.getDBacks());
                rosterWindow.getIcons().add(dbacksLogo);
                teamRightClicked = DBACKS;
                break;
            case GIANTS:
                rosterInfo.setItems(mainData.getGiants());
                rosterWindow.getIcons().add(giantsLogo);
                teamRightClicked = GIANTS;
                break;
            case BLUEJAYS:
                rosterInfo.setItems(mainData.getToronto());
                rosterWindow.getIcons().add(jaysLogo);
                teamRightClicked = BLUEJAYS;
                break;
            case REDSOX:
                rosterInfo.setItems(mainData.getRedSox());
                rosterWindow.getIcons().add(redsoxLogo);
                teamRightClicked = REDSOX;
                break;
            case YANKEES:
                rosterInfo.setItems(mainData.getYankees());
                rosterWindow.getIcons().add(yankeesLogo);
                teamRightClicked = YANKEES;
                break;
            case RAYS:
                rosterInfo.setItems(mainData.getRays());
                rosterWindow.getIcons().add(raysLogo);
                teamRightClicked = RAYS;
                break;
            case ORIOLES:
                rosterInfo.setItems(mainData.getOrioles());
                rosterWindow.getIcons().add(oriolesLogo);
                teamRightClicked = ORIOLES;
                break;
            case INDIANS:
                rosterInfo.setItems(mainData.getIndians());
                rosterWindow.getIcons().add(indiansLogo);
                teamRightClicked = INDIANS;
                break;
            case TWINS:
                rosterInfo.setItems(mainData.getTwins());
                rosterWindow.getIcons().add(twinsLogo);
                teamRightClicked = TWINS;
                break;
            case TIGERS:
                rosterInfo.setItems(mainData.getTigers());
                rosterWindow.getIcons().add(tigersLogo);
                teamRightClicked = TIGERS;
                break;
            case WHITESOX:
                rosterInfo.setItems(mainData.getWhiteSox());
                rosterWindow.getIcons().add(whitesoxLogo);
                teamRightClicked = WHITESOX;
                break;
            case ROYALS:
                rosterInfo.setItems(mainData.getRoyals());
                rosterWindow.getIcons().add(royalsLogo);
                teamRightClicked = ROYALS;
                break;
            case ASTROS:
                rosterInfo.setItems(mainData.getAstros());
                rosterWindow.getIcons().add(astrosLogo);
                teamRightClicked = ASTROS;
                break;
            case MARINERS:
                rosterInfo.setItems(mainData.getMariners());
                rosterWindow.getIcons().add(marinersLogo);
                teamRightClicked = MARINERS;
                break;
            case ATHLETICS:
                rosterInfo.setItems(mainData.getAs());
                rosterWindow.getIcons().add(asLogo);
                teamRightClicked = ATHLETICS;
                break;
            case ANGELS:
                rosterInfo.setItems(mainData.getAngels());
                rosterWindow.getIcons().add(angelsLogo);
                teamRightClicked = ANGELS;
                break;
            case RANGERS:
                rosterInfo.setItems(mainData.getRangers());
                rosterWindow.getIcons().add(rangersLogo);
                teamRightClicked = RANGERS;
                break;
        }
        setColoumns();
    }

    private void setColoumns() {
        name_col.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        position_col.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPosition()));
        fielding_points_col.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getFielding_points()));
        bat_hand_col.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getBat_hand()));
        speed_col.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getSpeed()));
        pitching_grade_col.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPitching_grade()));
        pitching_rating_col.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPitching_rating()));
        pitching_hand_col.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPitching_hand()));
    }

    public void showStage(){
        rosterWindow = new Stage();
        rosterWindow.setResizable(false);
        Pane comp = new Pane();
        rosterInfo = new TableView();
        rosterWindow.setTitle(teams[TEAM_SELECTED] + " Roster");
        rosterInfo.setMinWidth(755);
        rosterInfo.setMinHeight(390);

        rosterInfo.getColumns().add(name_col);
        rosterInfo.getColumns().add(position_col);
        rosterInfo.getColumns().add(fielding_points_col);
        rosterInfo.getColumns().add(bat_hand_col);
        rosterInfo.getColumns().add(speed_col);
        rosterInfo.getColumns().add(pitching_grade_col);
        rosterInfo.getColumns().add(pitching_rating_col);
        rosterInfo.getColumns().add(pitching_hand_col);

        name_col.setMinWidth(125);
        fielding_points_col.setMinWidth(100);
        pitching_grade_col.setMinWidth(100);
        pitching_hand_col.setMinWidth(100);
        pitching_rating_col.setMinWidth(100);

        comp.getChildren().add(rosterInfo);

        rosterInfo.setRowFactory( tv -> {
            TableRow<Player> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    if(playerCard.isShowing()) {
                        playerCard.close();
                    }
                    showPlayerCard();
                }
            });
            return row ;
        });

        Scene stageScene = new Scene(comp, 755, 390);
        rosterWindow.setScene(stageScene);
        rosterWindow.show();
    }

    private void allButtonsVisable() {
        rays.setVisible(true);
        bluejays.setVisible(true);
        yankees.setVisible(true);
        redsox.setVisible(true);
        orioles.setVisible(true);
        indians.setVisible(true);
        twins.setVisible(true);
        whitesox.setVisible(true);
        royals.setVisible(true);
        tigers.setVisible(true);
        as.setVisible(true);
        astros.setVisible(true);
        angels.setVisible(true);
        rangers.setVisible(true);
        mariners.setVisible(true);

        braves.setVisible(true);
        reds.setVisible(true);
        padres.setVisible(true);
        phillies.setVisible(true);
        marlins.setVisible(true);
        brewers.setVisible(true);
        cubs.setVisible(true);
        pirates.setVisible(true);
        cardinals.setVisible(true);
        mets.setVisible(true);
        nationals.setVisible(true);
        dbacks.setVisible(true);
        dodgers.setVisible(true);
        giants.setVisible(true);
        rockies.setVisible(true);
    }

    private void allButtonsInvisible() {
        rays.setVisible(false);
        bluejays.setVisible(false);
        yankees.setVisible(false);
        redsox.setVisible(false);
        orioles.setVisible(false);
        indians.setVisible(false);
        twins.setVisible(false);
        whitesox.setVisible(false);
        royals.setVisible(false);
        tigers.setVisible(false);
        as.setVisible(false);
        astros.setVisible(false);
        angels.setVisible(false);
        rangers.setVisible(false);
        mariners.setVisible(false);

        braves.setVisible(false);
        reds.setVisible(false);
        padres.setVisible(false);
        phillies.setVisible(false);
        marlins.setVisible(false);
        brewers.setVisible(false);
        cubs.setVisible(false);
        pirates.setVisible(false);
        cardinals.setVisible(false);
        mets.setVisible(false);
        nationals.setVisible(false);
        dbacks.setVisible(false);
        dodgers.setVisible(false);
        giants.setVisible(false);
        rockies.setVisible(false);
    }

    private void checkNumSelected() {
        switch (NUM_SELECTED) {
            case 0:
                homeTeamLabel.setVisible(true);
                awayTeamLabel.setVisible(false);
                break;
            case 1:
                homeTeamLabel.setVisible(false);
                awayTeamLabel.setVisible(true);
                break;
            case 2:
                homeTeamLabel.setVisible(false);
                awayTeamLabel.setVisible(false);
                allButtonsInvisible();
                team1LineupScreen();
                break;
        }
    }

    private void team1LineupScreen() {
        americanLeagueLogo.setVisible(false);
        nationalLeagueLogo.setVisible(false);
        apbaLogo.setVisible(false);

        eastA.setVisible(false);
        eastN.setVisible(false);
        centralA.setVisible(false);
        centralN.setVisible(false);
        westA.setVisible(false);
        westN.setVisible(false);
        home.setVisible(true);
        lineup.setVisible(true);
        choose.setVisible(false);
        awayTeamLabel.setVisible(false);
        rightClickInfo.setVisible(false);

        backToMain.setVisible(false);
        backToTeamSelect.setVisible(true);
        setLineup1.setVisible(true);

        catcher.setVisible(true);
        pitcher.setVisible(true);
        firstBase.setVisible(true);
        secondBase.setVisible(true);
        thirdBase.setVisible(true);
        shortstop.setVisible(true);
        leftField.setVisible(true);
        rightField.setVisible(true);
        centerField.setVisible(true);

        clearLineupPositions();

        teamSetupForChoiceBoxes(HOME_SELECTED);

    }

    private void team2LineupScreen() {
        setLineup1.setVisible(false);
        setLineup2.setVisible(true);
        away.setVisible(true);
        home.setVisible(false);
        clearLineupPositions();
        teamSetupForChoiceBoxes(AWAY_SELECTED);
    }

    private void teamSelectScreen() {
        start.setVisible(false);
        exit.setVisible(false);

        backToMain.setVisible(true);
        backToTeamSelect.setVisible(false);
        setLineup1.setVisible(false);
        setLineup2.setVisible(false);

        americanLeagueLogo.setVisible(true);
        nationalLeagueLogo.setVisible(true);
        apbaLogo.setVisible(true);

        westA.setVisible(true);
        eastA.setVisible(true);
        centralA.setVisible(true);
        westN.setVisible(true);
        eastN.setVisible(true);
        centralN.setVisible(true);
        choose.setVisible(true);
        homeTeamLabel.setVisible(true);
        rightClickInfo.setVisible(true);

        catcher.setVisible(false);
        pitcher.setVisible(false);
        firstBase.setVisible(false);
        secondBase.setVisible(false);
        thirdBase.setVisible(false);
        shortstop.setVisible(false);
        leftField.setVisible(false);
        rightField.setVisible(false);
        centerField.setVisible(false);

        allButtonsVisable();
    }

    private void mainScreen() {
        americanLeagueLogo.setVisible(false);
        nationalLeagueLogo.setVisible(false);
        apbaLogo.setVisible(true);
        firstBaseDot.setVisible(false);
        secondBaseDot.setVisible(false);
        thirdBaseDot.setVisible(false);

        start.setVisible(true);
        exit.setVisible(true);

        eastA.setVisible(false);
        eastN.setVisible(false);
        centralA.setVisible(false);
        centralN.setVisible(false);
        westA.setVisible(false);
        westN.setVisible(false);
        rightClickInfo.setVisible(false);

        backToMain.setVisible(false);

        awayFieldingPoints = 0;
        homeFieldingPoints = 0;

        resetClicks();
    }

    private void allButtonsOpaque() {
        reds.setOpacity(1);
        brewers.setOpacity(1);
        pirates.setOpacity(1);
        cardinals.setOpacity(1);
        cubs.setOpacity(1);
        mets.setOpacity(1);
        marlins.setOpacity(1);
        nationals.setOpacity(1);
        braves.setOpacity(1);
        phillies.setOpacity(1);
        giants.setOpacity(1);
        rockies.setOpacity(1);
        padres.setOpacity(1);
        dbacks.setOpacity(1);
        dodgers.setOpacity(1);

        as.setOpacity(1);
        astros.setOpacity(1);
        angels.setOpacity(1);
        mariners.setOpacity(1);
        rangers.setOpacity(1);
        yankees.setOpacity(1);
        redsox.setOpacity(1);
        bluejays.setOpacity(1);
        rays.setOpacity(1);
        orioles.setOpacity(1);
        tigers.setOpacity(1);
        indians.setOpacity(1);
        twins.setOpacity(1);
        royals.setOpacity(1);
        whitesox.setOpacity(1);

        resetClicks();

        HOME_SELECTED = 0;
        AWAY_SELECTED = 0;
    }

    private void resetClicks() {
        redsClicked = false;
        brewersClicked = false;
        piratesClicked = false;
        cubsClicked = false;
        cardinalsClicked = false;
        giantsClicked = false;
        padresClicked = false;
        dodgersClicked = false;
        rockiesClicked = false;
        dbacksClicked = false;
        bravesClicked = false;
        metsClicked = false;
        philliesClicked = false;
        marlinsClicked = false;
        nationalsClicked = false;

        redsoxClicked = false;
        yankeesClicked = false;
        bluejaysClicked = false;
        oriolesClicked = false;
        raysClicked = false;
        indiansClicked = false;
        tigersClicked = false;
        twinsClicked = false;
        royalsClicked = false;
        whitesoxClicked = false;
        asClicked = false;
        astrosClicked = false;
        rangersClicked = false;
        marinersClicked = false;
        angelsClicked = false;
    }

    private void clearLineupPositions() {
        catcher.getItems().clear();
        pitcher.getItems().clear();
        firstBase.getItems().clear();
        secondBase.getItems().clear();
        thirdBase.getItems().clear();
        shortstop.getItems().clear();
        leftField.getItems().clear();
        rightField.getItems().clear();
        centerField.getItems().clear();
    }

    private void setChoiceBoxes(Player r) {
        if (r.getPosition().equalsIgnoreCase("C")) {
            catcher.getItems().add(r.getName() + " (" + r.getFielding_points() + ")");
        }
        if (r.getPosition().equalsIgnoreCase("P") && !(r.getPitching_grade().contains("*"))) {
            pitcher.getItems().add(r.getName() + " (" + r.getFielding_points() + ")" + " (" +
                    r.getPitching_grade() + ")");
        }
        if (r.getPosition().equalsIgnoreCase("1B")) {
            firstBase.getItems().add(r.getName() + " (" + r.getFielding_points() + ")");
        }
        if (r.getPosition().equalsIgnoreCase("2B")) {
            secondBase.getItems().add(r.getName() + " (" + r.getFielding_points() + ")");
        }
        if (r.getPosition().equalsIgnoreCase("3B")) {
            thirdBase.getItems().add(r.getName() + " (" + r.getFielding_points() + ")");
        }
        if (r.getPosition().equalsIgnoreCase("SS")) {
            shortstop.getItems().add(r.getName() + " (" + r.getFielding_points() + ")");
        }
        if (r.getPosition().equalsIgnoreCase("OF")) {
            leftField.getItems().add(r.getName() + " (" + r.getFielding_points() + ")");
            rightField.getItems().add(r.getName() + " (" + r.getFielding_points() + ")");
            centerField.getItems().add(r.getName() + " (" + r.getFielding_points() + ")");
        }
    }

    private void teamSetupForChoiceBoxes(int team) {
        switch(team) {
            case BREWERS:
                for(Player r : mainData.getMilwaukee()) {
                    setChoiceBoxes(r);
                }
                break;
            case CUBS:
                for(Player r : mainData.getChicagoCubs()) {
                    setChoiceBoxes(r);
                }
                break;
            case CARDINALS:
                for(Player r : mainData.getCardinals()) {
                    setChoiceBoxes(r);
                }
                break;
            case PIRATES:
                for(Player r : mainData.getPirates()) {
                    setChoiceBoxes(r);
                }
                break;
            case REDS:
                for(Player r : mainData.getReds()) {
                    setChoiceBoxes(r);
                }
                break;
            case BRAVES:
                for(Player r : mainData.getBraves()) {
                    setChoiceBoxes(r);
                }
                break;
            case PHILLIES:
                for(Player r : mainData.getPhillies()) {
                    setChoiceBoxes(r);
                }
                break;
            case METS:
                for(Player r : mainData.getMets()) {
                    setChoiceBoxes(r);
                }
                break;
            case NATIONALS:
                for(Player r : mainData.getNationals()) {
                    setChoiceBoxes(r);
                }
                break;
            case MARLINS:
                for(Player r : mainData.getMarlins()) {
                    setChoiceBoxes(r);
                }
                break;
            case ROCKIES:
                for(Player r : mainData.getRockies()) {
                    setChoiceBoxes(r);
                }
                break;
            case PADRES:
                for(Player r : mainData.getPadres()) {
                    setChoiceBoxes(r);
                }
                break;
            case DODGERS:
                for(Player r : mainData.getDodgers()) {
                    setChoiceBoxes(r);
                }
                break;
            case DBACKS:
                for(Player r : mainData.getDBacks()) {
                    setChoiceBoxes(r);
                }
                break;
            case GIANTS:
                for(Player r : mainData.getGiants()) {
                    setChoiceBoxes(r);
                }
                break;
            case BLUEJAYS:
                for(Player r : mainData.getToronto()) {
                    setChoiceBoxes(r);
                }
                break;
            case REDSOX:
                for(Player r : mainData.getRedSox()) {
                    setChoiceBoxes(r);
                }
                break;
            case YANKEES:
                for(Player r : mainData.getYankees()) {
                    setChoiceBoxes(r);
                }
                break;
            case RAYS:
                for(Player r : mainData.getRays()) {
                    setChoiceBoxes(r);
                }
                break;
            case ORIOLES:
                for(Player r : mainData.getOrioles()) {
                    setChoiceBoxes(r);
                }
                break;
            case INDIANS:
                for(Player r : mainData.getIndians()) {
                    setChoiceBoxes(r);
                }
                break;
            case TWINS:
                for(Player r : mainData.getTwins()) {
                    setChoiceBoxes(r);
                }
                break;
            case TIGERS:
                for(Player r : mainData.getTigers()) {
                    setChoiceBoxes(r);
                }
                break;
            case WHITESOX:
                for(Player r : mainData.getWhiteSox()) {
                    setChoiceBoxes(r);
                }
                break;
            case ROYALS:
                for(Player r : mainData.getRoyals()) {
                    setChoiceBoxes(r);
                }
                break;
            case ASTROS:
                for(Player r : mainData.getAstros()) {
                    setChoiceBoxes(r);
                }
                break;
            case MARINERS:
                for(Player r : mainData.getMariners()) {
                    setChoiceBoxes(r);
                }
                break;
            case ATHLETICS:
                for(Player r : mainData.getAs()) {
                    setChoiceBoxes(r);
                }
                break;
            case ANGELS:
                for(Player r : mainData.getAngels()) {
                    setChoiceBoxes(r);
                }
                break;
            case RANGERS:
                for(Player r : mainData.getRangers()) {
                    setChoiceBoxes(r);
                }
                break;
        }
    }

    @FXML
    private void checkForValidHomeLineup() {
        if(catcher.getValue() != null && pitcher.getValue() != null && firstBase.getValue() != null &&
                secondBase.getValue() != null && thirdBase.getValue() != null && shortstop.getValue() != null &&
                leftField.getValue() != null && rightField.getValue() != null && centerField.getValue() != null &&
                noDuplicatePlayers() == 1) {
            fillHomeTeam();
            team2LineupScreen();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Lineup",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void checkForValidAwayLineup() {
        if(catcher.getValue() != null && pitcher.getValue() != null && firstBase.getValue() != null &&
                secondBase.getValue() != null && thirdBase.getValue() != null && shortstop.getValue() != null &&
                leftField.getValue() != null && rightField.getValue() != null && centerField.getValue() != null &&
                noDuplicatePlayers() == 1) {
            fillAwayTeam();
            homeBattingOrderScreen();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Lineup",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillHomeTeam() {
        switch(HOME_SELECTED) {
            case BREWERS:
                for(Player r : mainData.getMilwaukee()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case CUBS:
                for(Player r : mainData.getChicagoCubs()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case CARDINALS:
                for(Player r : mainData.getCardinals()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case PIRATES:
                for(Player r : mainData.getPirates()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case REDS:
                for(Player r : mainData.getReds()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case BRAVES:
                for(Player r : mainData.getBraves()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case PHILLIES:
                for(Player r : mainData.getPhillies()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case METS:
                for(Player r : mainData.getMets()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case NATIONALS:
                for(Player r : mainData.getNationals()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case MARLINS:
                for(Player r : mainData.getMarlins()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case ROCKIES:
                for(Player r : mainData.getRockies()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case PADRES:
                for(Player r : mainData.getPadres()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case DODGERS:
                for(Player r : mainData.getDodgers()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case DBACKS:
                for(Player r : mainData.getDBacks()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case GIANTS:
                for(Player r : mainData.getGiants()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case BLUEJAYS:
                for(Player r : mainData.getToronto()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case REDSOX:
                for(Player r : mainData.getRedSox()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case YANKEES:
                for(Player r : mainData.getYankees()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case RAYS:
                for(Player r : mainData.getRays()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case ORIOLES:
                for(Player r : mainData.getOrioles()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case INDIANS:
                for(Player r : mainData.getIndians()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case TWINS:
                for(Player r : mainData.getTwins()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case TIGERS:
                for(Player r : mainData.getTigers()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case WHITESOX:
                for(Player r : mainData.getWhiteSox()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case ROYALS:
                for(Player r : mainData.getRoyals()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case ASTROS:
                for(Player r : mainData.getAstros()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case MARINERS:
                for(Player r : mainData.getMariners()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case ATHLETICS:
                for(Player r : mainData.getAs()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case ANGELS:
                for(Player r : mainData.getAngels()) {
                    checkPlayerHomeTeam(r);
                }
                break;
            case RANGERS:
                for(Player r : mainData.getRangers()) {
                    checkPlayerHomeTeam(r);
                }
                break;
        }
    }

    private void fillAwayTeam() {
        switch(AWAY_SELECTED) {
            case BREWERS:
                for(Player r : mainData.getMilwaukee()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case CUBS:
                for(Player r : mainData.getChicagoCubs()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case CARDINALS:
                for(Player r : mainData.getCardinals()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case PIRATES:
                for(Player r : mainData.getPirates()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case REDS:
                for(Player r : mainData.getReds()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case BRAVES:
                for(Player r : mainData.getBraves()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case PHILLIES:
                for(Player r : mainData.getPhillies()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case METS:
                for(Player r : mainData.getMets()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case NATIONALS:
                for(Player r : mainData.getNationals()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case MARLINS:
                for(Player r : mainData.getMarlins()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case ROCKIES:
                for(Player r : mainData.getRockies()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case PADRES:
                for(Player r : mainData.getPadres()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case DODGERS:
                for(Player r : mainData.getDodgers()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case DBACKS:
                for(Player r : mainData.getDBacks()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case GIANTS:
                for(Player r : mainData.getGiants()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case BLUEJAYS:
                for(Player r : mainData.getToronto()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case REDSOX:
                for(Player r : mainData.getRedSox()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case YANKEES:
                for(Player r : mainData.getYankees()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case RAYS:
                for(Player r : mainData.getRays()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case ORIOLES:
                for(Player r : mainData.getOrioles()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case INDIANS:
                for(Player r : mainData.getIndians()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case TWINS:
                for(Player r : mainData.getTwins()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case TIGERS:
                for(Player r : mainData.getTigers()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case WHITESOX:
                for(Player r : mainData.getWhiteSox()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case ROYALS:
                for(Player r : mainData.getRoyals()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case ASTROS:
                for(Player r : mainData.getAstros()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case MARINERS:
                for(Player r : mainData.getMariners()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case ATHLETICS:
                for(Player r : mainData.getAs()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case ANGELS:
                for(Player r : mainData.getAngels()) {
                    checkPlayerAwayTeam(r);
                }
                break;
            case RANGERS:
                for(Player r : mainData.getRangers()) {
                    checkPlayerAwayTeam(r);
                }
                break;
        }
    }

    private void checkPlayerHomeTeam(Player r) {
        String[] catcherName = catcher.getValue().toString().split("\\s\\(");
        String[] pitcherName = pitcher.getValue().toString().split("\\s\\(");
        String[] firstBaseName = firstBase.getValue().toString().split("\\s\\(");
        String[] secondBaseName = secondBase.getValue().toString().split("\\s\\(");
        String[] thirdBaseName = thirdBase.getValue().toString().split("\\s\\(");
        String[] shortstopName = shortstop.getValue().toString().split("\\s\\(");
        String[] centerFieldName = centerField.getValue().toString().split("\\s\\(");
        String[] leftFieldName = leftField.getValue().toString().split("\\s\\(");
        String[] rightFieldName = rightField.getValue().toString().split("\\s\\(");

        if(r.getName().equalsIgnoreCase(catcherName[0])) {
            if(r.getPosition().equalsIgnoreCase("C")) {
                homeStartingLineup.add(r);
                homeFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(pitcherName[0])) {
            if(r.getPosition().equalsIgnoreCase("P") && !(r.getPitching_grade().contains("*"))) {
                homeStartingLineup.add(r);
                homeFieldingPoints += Integer.parseInt(r.getFielding_points());
                pitchingGradeHome = r.getPitching_grade();
                pitchingRatingHome = r.getPitching_rating();
            }
        }
        if(r.getName().equalsIgnoreCase(firstBaseName[0])) {
            if(r.getPosition().equalsIgnoreCase("1B")) {
                homeStartingLineup.add(r);
                homeFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(secondBaseName[0])) {
            if(r.getPosition().equalsIgnoreCase("2B")) {
                homeStartingLineup.add(r);
                homeFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(thirdBaseName[0])) {
            if(r.getPosition().equalsIgnoreCase("3B")) {
                homeStartingLineup.add(r);
                homeFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(shortstopName[0])) {
            if(r.getPosition().equalsIgnoreCase("SS")) {
                homeStartingLineup.add(r);
                homeFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(leftFieldName[0])) {
            if(r.getPosition().equalsIgnoreCase("OF")) {
                homeStartingLineup.add(r);
                homeFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(rightFieldName[0])) {
            if(r.getPosition().equalsIgnoreCase("OF")) {
                homeStartingLineup.add(r);
                homeFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(centerFieldName[0])) {
            if(r.getPosition().equalsIgnoreCase("OF")) {
                homeStartingLineup.add(r);
                homeFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
    }

    private void checkPlayerAwayTeam(Player r) {
        String[] catcherName = catcher.getValue().toString().split("\\s\\(");
        String[] pitcherName = pitcher.getValue().toString().split("\\s\\(");
        String[] firstBaseName = firstBase.getValue().toString().split("\\s\\(");
        String[] secondBaseName = secondBase.getValue().toString().split("\\s\\(");
        String[] thirdBaseName = thirdBase.getValue().toString().split("\\s\\(");
        String[] shortstopName = shortstop.getValue().toString().split("\\s\\(");
        String[] centerFieldName = centerField.getValue().toString().split("\\s\\(");
        String[] leftFieldName = leftField.getValue().toString().split("\\s\\(");
        String[] rightFieldName = rightField.getValue().toString().split("\\s\\(");

        if(r.getName().equalsIgnoreCase(catcherName[0])) {
            if(r.getPosition().equalsIgnoreCase("C")) {
                awayStartingLineup.add(r);
                awayFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(pitcherName[0])) {
            if(r.getPosition().equalsIgnoreCase("P") && !(r.getPitching_grade().contains("*"))) {
                awayStartingLineup.add(r);
                pitchingGradeAway = r.getPitching_grade();
                pitchingRatingAway = r.getPitching_rating();
                awayFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(firstBaseName[0])) {
            if(r.getPosition().equalsIgnoreCase("1B")) {
                awayStartingLineup.add(r);
                awayFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(secondBaseName[0])) {
            if(r.getPosition().equalsIgnoreCase("2B")) {
                awayStartingLineup.add(r);
                awayFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(thirdBaseName[0])) {
            if(r.getPosition().equalsIgnoreCase("3B")) {
                awayStartingLineup.add(r);
                awayFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(shortstopName[0])) {
            if(r.getPosition().equalsIgnoreCase("SS")) {
                awayStartingLineup.add(r);
                awayFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(leftFieldName[0])) {
            if(r.getPosition().equalsIgnoreCase("OF")) {
                awayStartingLineup.add(r);
                awayFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(rightFieldName[0])) {
            if(r.getPosition().equalsIgnoreCase("OF")) {
                awayStartingLineup.add(r);
                awayFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
        if(r.getName().equalsIgnoreCase(centerFieldName[0])) {
            if(r.getPosition().equalsIgnoreCase("OF")) {
                awayStartingLineup.add(r);
                awayFieldingPoints += Integer.parseInt(r.getFielding_points());
            }
        }
    }

    private void resetLineups() {
        homeStartingLineup.clear();
        awayStartingLineup.clear();
    }

    private int noDuplicatePlayers() {
        String[] catcherName = catcher.getValue().toString().split("\\s\\(");
        String[] pitcherName = pitcher.getValue().toString().split("\\s\\(");
        String[] firstBaseName = firstBase.getValue().toString().split("\\s\\(");
        String[] secondBaseName = secondBase.getValue().toString().split("\\s\\(");
        String[] thirdBaseName = thirdBase.getValue().toString().split("\\s\\(");
        String[] shortstopName = shortstop.getValue().toString().split("\\s\\(");
        String[] centerFieldName = centerField.getValue().toString().split("\\s\\(");
        String[] leftFieldName = leftField.getValue().toString().split("\\s\\(");
        String[] rightFieldName = rightField.getValue().toString().split("\\s\\(");

        if(!catcherName[0].equalsIgnoreCase(pitcherName[0]) && !catcherName[0].equalsIgnoreCase(firstBaseName[0]) &&
                !catcherName[0].equalsIgnoreCase(secondBaseName[0]) &&
                !catcherName[0].equalsIgnoreCase(thirdBaseName[0]) && !catcherName[0].equalsIgnoreCase(shortstopName[0])
                && !catcherName[0].equalsIgnoreCase(leftFieldName[0]) &&
                !catcherName[0].equalsIgnoreCase(rightFieldName[0]) &&
                !catcherName[0].equalsIgnoreCase(centerFieldName[0]) &&
                !firstBaseName[0].equalsIgnoreCase(pitcherName[0]) && !firstBaseName[0].equalsIgnoreCase(catcherName[0])
                && !firstBaseName[0].equalsIgnoreCase(secondBaseName[0]) &&
                !firstBaseName[0].equalsIgnoreCase(thirdBaseName[0]) &&
                !firstBaseName[0].equalsIgnoreCase(shortstopName[0])
                && !firstBaseName[0].equalsIgnoreCase(leftFieldName[0]) &&
                !firstBaseName[0].equalsIgnoreCase(rightFieldName[0]) &&
                !firstBaseName[0].equalsIgnoreCase(centerFieldName[0]) &&
                !secondBaseName[0].equalsIgnoreCase(pitcherName[0]) &&
                !secondBaseName[0].equalsIgnoreCase(firstBaseName[0]) &&
                !secondBaseName[0].equalsIgnoreCase(catcherName[0]) &&
                !secondBaseName[0].equalsIgnoreCase(thirdBaseName[0]) &&
                !secondBaseName[0].equalsIgnoreCase(shortstopName[0])
                && !secondBaseName[0].equalsIgnoreCase(leftFieldName[0]) &&
                !secondBaseName[0].equalsIgnoreCase(rightFieldName[0]) &&
                !secondBaseName[0].equalsIgnoreCase(centerFieldName[0]) &&
                !thirdBaseName[0].equalsIgnoreCase(pitcherName[0]) &&
                !thirdBaseName[0].equalsIgnoreCase(firstBaseName[0]) &&
                !thirdBaseName[0].equalsIgnoreCase(secondBaseName[0]) &&
                !thirdBaseName[0].equalsIgnoreCase(catcherName[0]) &&
                !thirdBaseName[0].equalsIgnoreCase(shortstopName[0])
                && !thirdBaseName[0].equalsIgnoreCase(leftFieldName[0]) &&
                !thirdBaseName[0].equalsIgnoreCase(rightFieldName[0]) &&
                !thirdBaseName[0].equalsIgnoreCase(centerFieldName[0]) &&
                !shortstopName[0].equalsIgnoreCase(pitcherName[0]) &&
                !shortstopName[0].equalsIgnoreCase(firstBaseName[0]) &&
                !shortstopName[0].equalsIgnoreCase(secondBaseName[0]) &&
                !shortstopName[0].equalsIgnoreCase(thirdBaseName[0]) &&
                !shortstopName[0].equalsIgnoreCase(catcherName[0])
                && !shortstopName[0].equalsIgnoreCase(leftFieldName[0]) &&
                !shortstopName[0].equalsIgnoreCase(rightFieldName[0]) &&
                !shortstopName[0].equalsIgnoreCase(centerFieldName[0]) &&
                !leftFieldName[0].equalsIgnoreCase(pitcherName[0]) &&
                !leftFieldName[0].equalsIgnoreCase(firstBaseName[0]) &&
                !leftFieldName[0].equalsIgnoreCase(secondBaseName[0]) &&
                !leftFieldName[0].equalsIgnoreCase(thirdBaseName[0]) &&
                !leftFieldName[0].equalsIgnoreCase(shortstopName[0])
                && !leftFieldName[0].equalsIgnoreCase(catcherName[0]) &&
                !leftFieldName[0].equalsIgnoreCase(rightFieldName[0]) &&
                !leftFieldName[0].equalsIgnoreCase(centerFieldName[0]) &&
                !rightFieldName[0].equalsIgnoreCase(pitcherName[0]) &&
                !rightFieldName[0].equalsIgnoreCase(firstBaseName[0]) &&
                !rightFieldName[0].equalsIgnoreCase(secondBaseName[0]) &&
                !rightFieldName[0].equalsIgnoreCase(thirdBaseName[0]) &&
                !rightFieldName[0].equalsIgnoreCase(shortstopName[0])
                && !rightFieldName[0].equalsIgnoreCase(leftFieldName[0]) &&
                !rightFieldName[0].equalsIgnoreCase(catcherName[0]) &&
                !rightFieldName[0].equalsIgnoreCase(centerFieldName[0]) &&
                !centerFieldName[0].equalsIgnoreCase(pitcherName[0]) &&
                !centerFieldName[0].equalsIgnoreCase(firstBaseName[0]) &&
                !centerFieldName[0].equalsIgnoreCase(secondBaseName[0]) &&
                !centerFieldName[0].equalsIgnoreCase(thirdBaseName[0]) &&
                !centerFieldName[0].equalsIgnoreCase(shortstopName[0])
                && !centerFieldName[0].equalsIgnoreCase(leftFieldName[0]) &&
                !centerFieldName[0].equalsIgnoreCase(rightFieldName[0]) &&
                !centerFieldName[0].equalsIgnoreCase(catcherName[0]) &&
                !pitcherName[0].equalsIgnoreCase(catcherName[0]) &&
                !pitcherName[0].equalsIgnoreCase(firstBaseName[0]) &&
                !pitcherName[0].equalsIgnoreCase(secondBaseName[0]) &&
                !pitcherName[0].equalsIgnoreCase(thirdBaseName[0]) &&
                !pitcherName[0].equalsIgnoreCase(shortstopName[0])
                && !pitcherName[0].equalsIgnoreCase(leftFieldName[0]) &&
                !pitcherName[0].equalsIgnoreCase(rightFieldName[0]) &&
                !pitcherName[0].equalsIgnoreCase(centerFieldName[0])) {
            return 1;
        } else {
            return 0;
        }
    }

    private void homeBattingOrderScreen() {
        clearAllBattingOrderTextFields();
        setDefaultOrder();

        setBattingOrderHome.setVisible(true);

        homeTeamBattingOrderLabel.setVisible(true);

        player0.setVisible(true);
        player1.setVisible(true);
        player2.setVisible(true);
        player3.setVisible(true);
        player4.setVisible(true);
        player5.setVisible(true);
        player6.setVisible(true);
        player7.setVisible(true);
        player8.setVisible(true);

        player0Order.setVisible(true);
        player1Order.setVisible(true);
        player2Order.setVisible(true);
        player3Order.setVisible(true);
        player4Order.setVisible(true);
        player5Order.setVisible(true);
        player6Order.setVisible(true);
        player7Order.setVisible(true);
        player8Order.setVisible(true);

        pitcher.setVisible(false);
        catcher.setVisible(false);
        firstBase.setVisible(false);
        secondBase.setVisible(false);
        thirdBase.setVisible(false);
        shortstop.setVisible(false);
        centerField.setVisible(false);
        leftField.setVisible(false);
        rightField.setVisible(false);
        away.setVisible(false);
        lineup.setVisible(false);
        setLineup2.setVisible(false);

        player0.setText(homeStartingLineup.get(0).getName());
        player1.setText(homeStartingLineup.get(1).getName());
        player2.setText(homeStartingLineup.get(2).getName());
        player3.setText(homeStartingLineup.get(3).getName());
        player4.setText(homeStartingLineup.get(4).getName());
        player5.setText(homeStartingLineup.get(5).getName());
        player6.setText(homeStartingLineup.get(6).getName());
        player7.setText(homeStartingLineup.get(7).getName());
        player8.setText(homeStartingLineup.get(8).getName());
    }

    private void awayBattingOrderScreen() {
        clearAllBattingOrderTextFields();
        setDefaultOrder();

        setBattingOrderHome.setVisible(false);
        setBattingOrderAway.setVisible(true);
        awayTeamBattingOrderLabel.setVisible(true);
        homeTeamBattingOrderLabel.setVisible(false);

        player0.setText(awayStartingLineup.get(0).getName());
        player1.setText(awayStartingLineup.get(1).getName());
        player2.setText(awayStartingLineup.get(2).getName());
        player3.setText(awayStartingLineup.get(3).getName());
        player4.setText(awayStartingLineup.get(4).getName());
        player5.setText(awayStartingLineup.get(5).getName());
        player6.setText(awayStartingLineup.get(6).getName());
        player7.setText(awayStartingLineup.get(7).getName());
        player8.setText(awayStartingLineup.get(8).getName());
    }

    @FXML
    private void checkValidHomeBattingOrder() {
        if (checkForValidNumbers() == 1) {
            setHomeBattingOrder();
            awayBattingOrderScreen();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Batting Order",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void checkValidAwayBattingOrder() {
        if (checkForValidNumbers() == 1) {
            setAwayBattingOrder();
            flashPlayBall();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Batting Order",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearAllBattingOrderTextFields() {
        player0.clear();
        player1.clear();
        player2.clear();
        player3.clear();
        player4.clear();
        player5.clear();
        player6.clear();
        player7.clear();
        player8.clear();
    }

    private void setBattingOrderTextFieldsInvisible() {
        player0.setVisible(false);
        player1.setVisible(false);
        player2.setVisible(false);
        player3.setVisible(false);
        player4.setVisible(false);
        player5.setVisible(false);
        player6.setVisible(false);
        player7.setVisible(false);
        player8.setVisible(false);
        player0Order.setVisible(false);
        player1Order.setVisible(false);
        player2Order.setVisible(false);
        player3Order.setVisible(false);
        player4Order.setVisible(false);
        player5Order.setVisible(false);
        player6Order.setVisible(false);
        player7Order.setVisible(false);
        player8Order.setVisible(false);
    }

    private void setDefaultOrder() {
        player0Order.setText("1");
        player1Order.setText("2");
        player2Order.setText("3");
        player3Order.setText("4");
        player4Order.setText("5");
        player5Order.setText("6");
        player6Order.setText("7");
        player7Order.setText("8");
        player8Order.setText("9");
    }

    private int checkForValidNumbers() {
        try {
            int player0 = Integer.parseInt(player0Order.getText());
            int player1 = Integer.parseInt(player1Order.getText());
            int player2 = Integer.parseInt(player2Order.getText());
            int player3 = Integer.parseInt(player3Order.getText());
            int player4 = Integer.parseInt(player4Order.getText());
            int player5 = Integer.parseInt(player5Order.getText());
            int player6 = Integer.parseInt(player6Order.getText());
            int player7 = Integer.parseInt(player7Order.getText());
            int player8 = Integer.parseInt(player8Order.getText());
            if (player0 != player1 && player0 != player2 && player0 != player3 && player0 != player4 &&
                    player0 != player5 && player0 != player6 && player0 != player7 && player0 != player8 &&
                    player1 != player2 && player1 != player3 && player1 != player4 &&
                    player1 != player5 && player1 != player6 && player1 != player7 && player1 != player8 &&
                    player2 != player3 && player2 != player4 && player2 != player5 && player2 != player6 &&
                    player2 != player7 && player2 != player8 && player3 != player4 &&
                    player3 != player5 && player3 != player6 && player3 != player7 && player3 != player8 &&
                    player4 != player5 && player4 != player6 && player4 != player7 && player4 != player8 &&
                    player5 != player6 && player5 != player7 && player5 != player8 && player6 != player7 &&
                    player6 != player8 && player7 != player8 && player0 >= 1 && player0 <= 9 && player1 >= 1 &&
                    player1 <= 9 && player2 >= 1 && player2 <= 9 && player3 >= 1 && player3 <= 9 && player4 >= 1 &&
                    player4 <= 9 && player5 >= 1 && player5 <= 9 && player6 >= 1 && player6 <= 9 && player7 >= 1 &&
                    player7 <= 9 && player8 >= 1 && player8 <= 9) {
                return 1;
            } else {
                return 0;
            }
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void setHomeBattingOrder() {
        Player player = new Player();
        for(int i = 0; i <= 9; i++) {
            homeBattingOrder.add(i, player);
        }
        int player0 = Integer.parseInt(player0Order.getText());
        int player1 = Integer.parseInt(player1Order.getText());
        int player2 = Integer.parseInt(player2Order.getText());
        int player3 = Integer.parseInt(player3Order.getText());
        int player4 = Integer.parseInt(player4Order.getText());
        int player5 = Integer.parseInt(player5Order.getText());
        int player6 = Integer.parseInt(player6Order.getText());
        int player7 = Integer.parseInt(player7Order.getText());
        int player8 = Integer.parseInt(player8Order.getText());

        setHome(0, player0 - 1);
        setHome(1, player1 - 1);
        setHome(2, player2 - 1);
        setHome(3, player3 - 1);
        setHome(4, player4 - 1);
        setHome(5, player5 - 1);
        setHome(6, player6 - 1);
        setHome(7, player7 - 1);
        setHome(8, player8 - 1);
    }

    private void setAwayBattingOrder() {
        Player player = new Player();
        for(int i = 0; i <= 9; i++) {
            awayBattingOrder.add(i, player);
        }
        int player0 = Integer.parseInt(player0Order.getText());
        int player1 = Integer.parseInt(player1Order.getText());
        int player2 = Integer.parseInt(player2Order.getText());
        int player3 = Integer.parseInt(player3Order.getText());
        int player4 = Integer.parseInt(player4Order.getText());
        int player5 = Integer.parseInt(player5Order.getText());
        int player6 = Integer.parseInt(player6Order.getText());
        int player7 = Integer.parseInt(player7Order.getText());
        int player8 = Integer.parseInt(player8Order.getText());

        setAway(0, player0 - 1);
        setAway(1, player1 - 1);
        setAway(2, player2 - 1);
        setAway(3, player3 - 1);
        setAway(4, player4 - 1);
        setAway(5, player5 - 1);
        setAway(6, player6 - 1);
        setAway(7, player7 - 1);
        setAway(8, player8 - 1);
    }

    private void setHome(int x, int y) {
        Player temp = homeStartingLineup.get(x);
        homeBattingOrder.set(y, temp);
    }

    private void setAway(int x, int y) {
        Player temp = awayStartingLineup.get(x);
        awayBattingOrder.set(y, temp);
    }

    private void flashPlayBall() {
        setBattingOrderTextFieldsInvisible();
        awayTeamBattingOrderLabel.setVisible(false);
        setBattingOrderAway.setVisible(false);
        backToTeamSelect.setVisible(false);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), evt -> play.setVisible(false)),
                new KeyFrame(Duration.seconds(1), evt -> play.setVisible(true)),
                new KeyFrame(Duration.seconds(2), evt -> ball.setVisible(false)),
                new KeyFrame(Duration.seconds(1), evt -> ball.setVisible(true)));
        timeline.setCycleCount(2);
        timeline.play();
        timeline.setOnFinished(event -> {
            quitToTeamSelect.setVisible(true);
            calculateAwayFieldingGrade();
            calculateHomeFieldingGrade();
            newGame = new Game();
            newGame.startGame(homeBattingOrder, awayBattingOrder, thirdBaseDot, secondBaseDot, firstBaseDot,
                    pitchingGradeAway, pitchingGradeHome, pitchingRatingAway, pitchingRatingHome,
                    awayFieldingGrade, homeFieldingGrade, awayImage, homeImage);
        });
    }

    private void calculateAwayFieldingGrade() {
        if (awayFieldingPoints >= 41) {
            awayFieldingGrade = 1;
        } else if(awayFieldingPoints >= 36) {
            awayFieldingGrade = 2;
        } else {
            awayFieldingGrade = 3;
        }
    }

    private void calculateHomeFieldingGrade() {
        if (homeFieldingPoints >= 41) {
            homeFieldingGrade = 1;
        } else if(homeFieldingPoints >= 36) {
            homeFieldingGrade = 2;
        } else {
            homeFieldingGrade = 3;
        }
    }

    private void showPlayerCard() {
        String selectedItem = rosterInfo.getItems().get(rosterInfo.getSelectionModel().getFocusedIndex()).toString();
        String[] data = selectedItem.split(": ");
        String posSplit = data[2];
        String[] posData = posSplit.split(" ");
        String pos = posData[0];
        String nameSplit = data[1];
        String[] nameData = nameSplit.split(" ");
        String name = nameData[0] + " " + nameData[1];
        playerCard = new Stage();
        playerCard.setResizable(false);
        AnchorPane comp = new AnchorPane();
        playerCard.setTitle("Player Card");
        playerCard.setMinWidth(215);
        playerCard.setMinHeight(250);

        Label firstName = new Label(nameData[0]);
        Label lastName = new Label(nameData[1]);
        firstName.setStyle("-fx-font: 18 arial;");
        lastName.setStyle("-fx-font: 24 arial; -fx-font-weight: bold;");
        AnchorPane.setLeftAnchor(firstName, 0.0);
        AnchorPane.setRightAnchor(firstName, 0.0);
        firstName.setAlignment(Pos.CENTER);
        AnchorPane.setLeftAnchor(lastName, 0.0);
        AnchorPane.setRightAnchor(lastName, 0.0);
        lastName.setAlignment(Pos.CENTER);
        lastName.setLayoutY(20);
        firstName.setTextFill(Color.web("#DC143C"));
        lastName.setTextFill(Color.web("#DC143C"));
        Label position = new Label();
        AnchorPane.setLeftAnchor(position, 0.0);
        AnchorPane.setRightAnchor(position, 0.0);
        position.setAlignment(Pos.CENTER);
        position.setTextFill(Color.web("#DC143C"));
        position.setStyle("-fx-font: 14 arial;");
        position.setLayoutY(45);
        Label bats = new Label();
        Label throwHand = new Label();


        initLabels(comp, name, position, bats, throwHand, pos);

        comp.getChildren().add(firstName);
        comp.getChildren().add(lastName);
        comp.getChildren().add(position);
        comp.getChildren().add(bats);
        comp.getChildren().add(throwHand);

        Scene stageScene = new Scene(comp, 215, 250);
        playerCard.setScene(stageScene);
        playerCard.getIcons().add(APBALogo);
        playerCard.show();
    }

    private void initLabels(AnchorPane comp, String name, Label pos, Label bats, Label throwHand, String position) {
        switch(teamRightClicked) {
            case BREWERS:
                for(Player r : mainData.getMilwaukee()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case CUBS:
                for(Player r : mainData.getChicagoCubs()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case CARDINALS:
                for(Player r : mainData.getCardinals()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case PIRATES:
                for(Player r : mainData.getPirates()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case REDS:
                for(Player r : mainData.getReds()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case BRAVES:
                for(Player r : mainData.getBraves()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case PHILLIES:
                for(Player r : mainData.getPhillies()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case METS:
                for(Player r : mainData.getMets()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case NATIONALS:
                for(Player r : mainData.getNationals()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case MARLINS:
                for(Player r : mainData.getMarlins()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case ROCKIES:
                for(Player r : mainData.getRockies()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case PADRES:
                for(Player r : mainData.getPadres()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case DODGERS:
                for(Player r : mainData.getDodgers()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case DBACKS:
                for(Player r : mainData.getDBacks()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case GIANTS:
                for(Player r : mainData.getGiants()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case BLUEJAYS:
                for(Player r : mainData.getToronto()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case REDSOX:
                for(Player r : mainData.getRedSox()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case YANKEES:
                for(Player r : mainData.getYankees()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case RAYS:
                for(Player r : mainData.getRays()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case ORIOLES:
                for(Player r : mainData.getOrioles()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case INDIANS:
                for(Player r : mainData.getIndians()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case TWINS:
                for(Player r : mainData.getTwins()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case TIGERS:
                for(Player r : mainData.getTigers()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case WHITESOX:
                for(Player r : mainData.getWhiteSox()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case ROYALS:
                for(Player r : mainData.getRoyals()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case ASTROS:
                for(Player r : mainData.getAstros()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case MARINERS:
                for(Player r : mainData.getMariners()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case ATHLETICS:
                for(Player r : mainData.getAs()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case ANGELS:
                for(Player r : mainData.getAngels()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
            case RANGERS:
                for(Player r : mainData.getRangers()) {
                    if(name.equalsIgnoreCase(r.getName())) {
                        setLabels(r, comp);
                        checkPos(r, pos, throwHand, bats, position);
                    }
                }
                break;
        }
    }

    private void setLabels(Player r, AnchorPane comp) {
        Label eleven1;
        Label twelve1;
        Label thirteen1;
        Label fourteen1;
        Label fifteen1;
        Label sixteen1;
        Label twentyone1;
        Label twentytwo1;
        Label twentythree1;
        Label twentyfour1;
        Label twentyfive1;
        Label twentysix1;
        Label thirtyone1;
        Label thirtytwo1;
        Label thirtythree1;
        Label thirtyfour1;
        Label thirtyfive1;
        Label thirtysix1;
        Label fourtyone1;
        Label fourtytwo1;
        Label fourtythree1;
        Label fourtyfour1;
        Label fourtyfive1;
        Label fourtysix1;
        Label fiftyone1;
        Label fiftytwo1;
        Label fiftythree1;
        Label fiftyfour1;
        Label fiftyfive1;
        Label fiftysix1;
        Label sixtyone1;
        Label sixtytwo1;
        Label sixtythree1;
        Label sixtyfour1;
        Label sixtyfive1;
        Label sixtysix1;

        Label eleven = new Label("11- ");
        eleven.setStyle("-fx-font: 14 Times New Roman;");
        eleven.setStyle("-fx-font-weight: bold");
        eleven.setLayoutY(75);
        Label twelve = new Label("12- ");
        twelve.setStyle("-fx-font: 14 Times New Roman;");
        twelve.setStyle("-fx-font-weight: bold");
        twelve.setLayoutY(90);
        Label thirteen = new Label("13- ");
        thirteen.setStyle("-fx-font: 14 Times New Roman;");
        thirteen.setStyle("-fx-font-weight: bold");
        thirteen.setLayoutY(105);
        Label fourteen = new Label("14- ");
        fourteen.setStyle("-fx-font: 14 Times New Roman;");
        fourteen.setStyle("-fx-font-weight: bold");
        fourteen.setLayoutY(120);
        Label fifteen = new Label("15- ");
        fifteen.setStyle("-fx-font: 14 Times New Roman;");
        fifteen.setStyle("-fx-font-weight: bold");
        fifteen.setLayoutY(135);
        Label sixteen = new Label("16- ");
        sixteen.setStyle("-fx-font: 14 Times New Roman;");
        sixteen.setStyle("-fx-font-weight: bold");
        sixteen.setLayoutY(150);
        Label twentyone = new Label("21- ");
        twentyone.setStyle("-fx-font: 14 Times New Roman;");
        twentyone.setStyle("-fx-font-weight: bold");
        twentyone.setLayoutY(165);
        Label twentytwo = new Label("22- ");
        twentytwo.setStyle("-fx-font: 14 Times New Roman;");
        twentytwo.setStyle("-fx-font-weight: bold");
        twentytwo.setLayoutY(180);
        Label twentythree = new Label("23- ");
        twentythree.setStyle("-fx-font: 14 Times New Roman;");
        twentythree.setStyle("-fx-font-weight: bold");
        twentythree.setLayoutY(195);
        Label twentyfour = new Label("24- ");
        twentyfour.setStyle("-fx-font: 14 Times New Roman;");
        twentyfour.setStyle("-fx-font-weight: bold");
        twentyfour.setLayoutY(210);
        Label twentyfive = new Label("25- ");
        twentyfive.setStyle("-fx-font: 14 Times New Roman;");
        twentyfive.setStyle("-fx-font-weight: bold");
        twentyfive.setLayoutY(225);
        Label twentysix = new Label("26- ");
        twentysix.setStyle("-fx-font: 14 Times New Roman;");
        twentysix.setStyle("-fx-font-weight: bold");
        twentysix.setLayoutY(240);
        Label thirtyone = new Label("31- ");
        thirtyone.setStyle("-fx-font: 14 Times New Roman;");
        thirtyone.setStyle("-fx-font-weight: bold");
        thirtyone.setLayoutX(75);
        thirtyone.setLayoutY(75);
        Label thirtytwo = new Label("32- ");
        thirtytwo.setStyle("-fx-font: 14 Times New Roman;");
        thirtytwo.setStyle("-fx-font-weight: bold");
        thirtytwo.setLayoutX(75);
        thirtytwo.setLayoutY(90);
        Label thirtythree = new Label("33- ");
        thirtythree.setStyle("-fx-font: 14 Times New Roman;");
        thirtythree.setStyle("-fx-font-weight: bold");
        thirtythree.setLayoutX(75);
        thirtythree.setLayoutY(105);
        Label thirtyfour = new Label("34- ");
        thirtyfour.setStyle("-fx-font: 14 Times New Roman;");
        thirtyfour.setStyle("-fx-font-weight: bold");
        thirtyfour.setLayoutX(75);
        thirtyfour.setLayoutY(120);
        Label thirtyfive = new Label("35- ");
        thirtyfive.setStyle("-fx-font: 14 Times New Roman;");
        thirtyfive.setStyle("-fx-font-weight: bold");
        thirtyfive.setLayoutX(75);
        thirtyfive.setLayoutY(135);
        Label thirtysix = new Label("36- ");
        thirtysix.setStyle("-fx-font: 14 Times New Roman;");
        thirtysix.setStyle("-fx-font-weight: bold");
        thirtysix.setLayoutX(75);
        thirtysix.setLayoutY(150);
        Label fourtyone = new Label("41- ");
        fourtyone.setStyle("-fx-font: 14 Times New Roman;");
        fourtyone.setStyle("-fx-font-weight: bold");
        fourtyone.setLayoutX(75);
        fourtyone.setLayoutY(165);
        Label fourtytwo = new Label("42- ");
        fourtytwo.setStyle("-fx-font: 14 Times New Roman;");
        fourtytwo.setStyle("-fx-font-weight: bold");
        fourtytwo.setLayoutX(75);
        fourtytwo.setLayoutY(180);
        Label fourtythree = new Label("43- ");
        fourtythree.setStyle("-fx-font: 14 Times New Roman;");
        fourtythree.setStyle("-fx-font-weight: bold");
        fourtythree.setLayoutX(75);
        fourtythree.setLayoutY(195);
        Label fourtyfour = new Label("44- ");
        fourtyfour.setStyle("-fx-font: 14 Times New Roman;");
        fourtyfour.setStyle("-fx-font-weight: bold");
        fourtyfour.setLayoutX(75);
        fourtyfour.setLayoutY(210);
        Label fourtyfive = new Label("45- ");
        fourtyfive.setStyle("-fx-font: 14 Times New Roman;");
        fourtyfive.setStyle("-fx-font-weight: bold");
        fourtyfive.setLayoutX(75);
        fourtyfive.setLayoutY(225);
        Label fourtysix = new Label("46- ");
        fourtysix.setStyle("-fx-font: 14 Times New Roman;");
        fourtysix.setStyle("-fx-font-weight: bold");
        fourtysix.setLayoutX(75);
        fourtysix.setLayoutY(240);
        Label fiftyone = new Label("51- ");
        fiftyone.setStyle("-fx-font: 14 Times New Roman;");
        fiftyone.setStyle("-fx-font-weight: bold");
        fiftyone.setLayoutX(150);
        fiftyone.setLayoutY(75);
        Label fiftytwo = new Label("52- ");
        fiftytwo.setStyle("-fx-font: 14 Times New Roman;");
        fiftytwo.setStyle("-fx-font-weight: bold");
        fiftytwo.setLayoutX(150);
        fiftytwo.setLayoutY(90);
        Label fiftythree = new Label("53- ");
        fiftythree.setStyle("-fx-font: 14 Times New Roman;");
        fiftythree.setStyle("-fx-font-weight: bold");
        fiftythree.setLayoutX(150);
        fiftythree.setLayoutY(105);
        Label fiftyfour = new Label("54- ");
        fiftyfour.setStyle("-fx-font: 14 Times New Roman;");
        fiftyfour.setStyle("-fx-font-weight: bold");
        fiftyfour.setLayoutX(150);
        fiftyfour.setLayoutY(120);
        Label fiftyfive = new Label("55- ");
        fiftyfive.setStyle("-fx-font: 14 Times New Roman;");
        fiftyfive.setStyle("-fx-font-weight: bold");
        fiftyfive.setLayoutX(150);
        fiftyfive.setLayoutY(135);
        Label fiftysix = new Label("56- ");
        fiftysix.setStyle("-fx-font: 14 Times New Roman;");
        fiftysix.setStyle("-fx-font-weight: bold");
        fiftysix.setLayoutX(150);
        fiftysix.setLayoutY(150);
        Label sixtyone = new Label("61- ");
        sixtyone.setStyle("-fx-font: 14 Times New Roman;");
        sixtyone.setStyle("-fx-font-weight: bold");
        sixtyone.setLayoutX(150);
        sixtyone.setLayoutY(165);
        Label sixtytwo = new Label("62- ");
        sixtytwo.setStyle("-fx-font: 14 Times New Roman;");
        sixtytwo.setStyle("-fx-font-weight: bold");
        sixtytwo.setLayoutX(150);
        sixtytwo.setLayoutY(180);
        Label sixtythree = new Label("63- ");
        sixtythree.setStyle("-fx-font: 14 Times New Roman;");
        sixtythree.setStyle("-fx-font-weight: bold");
        sixtythree.setLayoutX(150);
        sixtythree.setLayoutY(195);
        Label sixtyfour = new Label("64- ");
        sixtyfour.setStyle("-fx-font: 14 Times New Roman;");
        sixtyfour.setStyle("-fx-font-weight: bold");
        sixtyfour.setLayoutX(150);
        sixtyfour.setLayoutY(210);
        Label sixtyfive = new Label("65- ");
        sixtyfive.setStyle("-fx-font: 14 Times New Roman;");
        sixtyfive.setStyle("-fx-font-weight: bold");
        sixtyfive.setLayoutX(150);
        sixtyfive.setLayoutY(225);
        Label sixtysix = new Label("66- ");
        sixtysix.setStyle("-fx-font: 14 Times New Roman;");
        sixtysix.setStyle("-fx-font-weight: bold");
        sixtysix.setLayoutX(150);
        sixtysix.setLayoutY(240);

        if(!r.getOne_one2().equalsIgnoreCase("")) {
            eleven1 = new Label(r.getOne_one() + " - " + r.getOne_one2());
            eleven1.setStyle("-fx-font: 14 Times New Roman;");
            eleven1.setStyle("-fx-font-weight: bold");
            eleven1.setLayoutX(25);
            eleven1.setLayoutY(75);
            twelve1 = new Label(r.getOne_two() + " - " + r.getOne_two2());
            twelve1.setStyle("-fx-font: 14 Times New Roman;");
            twelve1.setStyle("-fx-font-weight: bold");
            twelve1.setLayoutX(25);
            twelve1.setLayoutY(90);
            thirteen1 = new Label(r.getOne_three() + " - " + r.getOne_three2());
            thirteen1.setStyle("-fx-font: 14 Times New Roman;");
            thirteen1.setStyle("-fx-font-weight: bold");
            thirteen1.setLayoutX(25);
            thirteen1.setLayoutY(105);
            fourteen1 = new Label(r.getOne_four() + " - " + r.getOne_four2());
            fourteen1.setStyle("-fx-font: 14 Times New Roman;");
            fourteen1.setStyle("-fx-font-weight: bold");
            fourteen1.setLayoutX(25);
            fourteen1.setLayoutY(120);
            fifteen1 = new Label(r.getOne_five() + " - " + r.getOne_five2());
            fifteen1.setStyle("-fx-font: 14 Times New Roman;");
            fifteen1.setStyle("-fx-font-weight: bold");
            fifteen1.setLayoutX(25);
            fifteen1.setLayoutY(135);
            sixteen1 = new Label(r.getOne_six() + " - " + r.getOne_six2());
            sixteen1.setStyle("-fx-font: 14 Times New Roman;");
            sixteen1.setStyle("-fx-font-weight: bold");
            sixteen1.setLayoutX(25);
            sixteen1.setLayoutY(150);
            twentyone1 = new Label(r.getTwo_one() + " - " + r.getTwo_one2());
            twentyone1.setStyle("-fx-font: 14 Times New Roman;");
            twentyone1.setStyle("-fx-font-weight: bold");
            twentyone1.setLayoutX(25);
            twentyone1.setLayoutY(165);
            twentytwo1 = new Label(r.getTwo_two() + " - " + r.getTwo_two2());
            twentytwo1.setStyle("-fx-font: 14 Times New Roman;");
            twentytwo1.setStyle("-fx-font-weight: bold");
            twentytwo1.setLayoutX(25);
            twentytwo1.setLayoutY(180);
            twentythree1 = new Label(r.getTwo_three() + " - " + r.getTwo_three2());
            twentythree1.setStyle("-fx-font: 14 Times New Roman;");
            twentythree1.setStyle("-fx-font-weight: bold");
            twentythree1.setLayoutX(25);
            twentythree1.setLayoutY(195);
            twentyfour1 = new Label(r.getTwo_four() + " - " + r.getTwo_four2());
            twentyfour1.setStyle("-fx-font: 14 Times New Roman;");
            twentyfour1.setStyle("-fx-font-weight: bold");
            twentyfour1.setLayoutX(25);
            twentyfour1.setLayoutY(210);
            twentyfive1 = new Label(r.getTwo_five() + " - " + r.getTwo_five2());
            twentyfive1.setStyle("-fx-font: 14 Times New Roman;");
            twentyfive1.setStyle("-fx-font-weight: bold");
            twentyfive1.setLayoutX(25);
            twentyfive1.setLayoutY(225);
            twentysix1 = new Label(r.getTwo_six() + " - " + r.getTwo_six2());
            twentysix1.setStyle("-fx-font: 14 Times New Roman;");
            twentysix1.setStyle("-fx-font-weight: bold");
            twentysix1.setLayoutX(25);
            twentysix1.setLayoutY(240);

            thirtyone1 = new Label(r.getThree_one() + " - " + r.getThree_one2());
            thirtyone1.setStyle("-fx-font: 14 Times New Roman;");
            thirtyone1.setStyle("-fx-font-weight: bold");
            thirtyone1.setLayoutX(100);
            thirtyone1.setLayoutY(75);
            thirtytwo1 = new Label(r.getThree_two() + " - " + r.getThree_two2());
            thirtytwo1.setStyle("-fx-font: 14 Times New Roman;");
            thirtytwo1.setStyle("-fx-font-weight: bold");
            thirtytwo1.setLayoutX(100);
            thirtytwo1.setLayoutY(90);
            thirtythree1 = new Label(r.getThree_three() + " - " + r.getThree_three2());
            thirtythree1.setStyle("-fx-font: 14 Times New Roman;");
            thirtythree1.setStyle("-fx-font-weight: bold");
            thirtythree1.setLayoutX(100);
            thirtythree1.setLayoutY(105);
            thirtyfour1 = new Label(r.getThree_four() + " - " + r.getThree_four2());
            thirtyfour1.setStyle("-fx-font: 14 Times New Roman;");
            thirtyfour1.setStyle("-fx-font-weight: bold");
            thirtyfour1.setLayoutX(100);
            thirtyfour1.setLayoutY(120);
            thirtyfive1 = new Label(r.getThree_five() + " - " + r.getThree_five2());
            thirtyfive1.setStyle("-fx-font: 14 Times New Roman;");
            thirtyfive1.setStyle("-fx-font-weight: bold");
            thirtyfive1.setLayoutX(100);
            thirtyfive1.setLayoutY(135);
            thirtysix1 = new Label(r.getThree_six() + " - " + r.getThree_six2());
            thirtysix1.setStyle("-fx-font: 14 Times New Roman;");
            thirtysix1.setStyle("-fx-font-weight: bold");
            thirtysix1.setLayoutX(100);
            thirtysix1.setLayoutY(150);
            fourtyone1 = new Label(r.getFour_one() + " - " + r.getFour_one2());
            fourtyone1.setStyle("-fx-font: 14 Times New Roman;");
            fourtyone1.setStyle("-fx-font-weight: bold");
            fourtyone1.setLayoutX(100);
            fourtyone1.setLayoutY(165);
            fourtytwo1 = new Label(r.getFour_two() + " - " + r.getFour_two2());
            fourtytwo1.setStyle("-fx-font: 14 Times New Roman;");
            fourtytwo1.setStyle("-fx-font-weight: bold");
            fourtytwo1.setLayoutX(100);
            fourtytwo1.setLayoutY(180);
            fourtythree1 = new Label(r.getFour_three() + " - " + r.getFour_three2());
            fourtythree1.setStyle("-fx-font: 14 Times New Roman;");
            fourtythree1.setStyle("-fx-font-weight: bold");
            fourtythree1.setLayoutX(100);
            fourtythree1.setLayoutY(195);
            fourtyfour1 = new Label(r.getFour_four() + " - " + r.getFour_four2());
            fourtyfour1.setStyle("-fx-font: 14 Times New Roman;");
            fourtyfour1.setStyle("-fx-font-weight: bold");
            fourtyfour1.setLayoutX(100);
            fourtyfour1.setLayoutY(210);
            fourtyfive1 = new Label(r.getFour_five() + " - " + r.getFour_five2());
            fourtyfive1.setStyle("-fx-font: 14 Times New Roman;");
            fourtyfive1.setStyle("-fx-font-weight: bold");
            fourtyfive1.setLayoutX(100);
            fourtyfive1.setLayoutY(225);
            fourtysix1 = new Label(r.getFour_six() + " - " + r.getFour_six2());
            fourtysix1.setStyle("-fx-font: 14 Times New Roman;");
            fourtysix1.setStyle("-fx-font-weight: bold");
            fourtysix1.setLayoutX(100);
            fourtysix1.setLayoutY(240);

            fiftyone1 = new Label(r.getFive_one() + " - " + r.getFive_one2());
            fiftyone1.setStyle("-fx-font: 14 Times New Roman;");
            fiftyone1.setStyle("-fx-font-weight: bold");
            fiftyone1.setLayoutX(175);
            fiftyone1.setLayoutY(75);
            fiftytwo1 = new Label(r.getFive_two() + " - " + r.getFive_two2());
            fiftytwo1.setStyle("-fx-font: 14 Times New Roman;");
            fiftytwo1.setStyle("-fx-font-weight: bold");
            fiftytwo1.setLayoutX(175);
            fiftytwo1.setLayoutY(90);
            fiftythree1 = new Label(r.getFive_three() + " - " + r.getFive_three2());
            fiftythree1.setStyle("-fx-font: 14 Times New Roman;");
            fiftythree1.setStyle("-fx-font-weight: bold");
            fiftythree1.setLayoutX(175);
            fiftythree1.setLayoutY(105);
            fiftyfour1 = new Label(r.getFive_four() + " - " + r.getFive_four2());
            fiftyfour1.setStyle("-fx-font: 14 Times New Roman;");
            fiftyfour1.setStyle("-fx-font-weight: bold");
            fiftyfour1.setLayoutX(175);
            fiftyfour1.setLayoutY(120);
            fiftyfive1 = new Label(r.getFive_five() + " - " + r.getFive_five2());
            fiftyfive1.setStyle("-fx-font: 14 Times New Roman;");
            fiftyfive1.setStyle("-fx-font-weight: bold");
            fiftyfive1.setLayoutX(175);
            fiftyfive1.setLayoutY(135);
            fiftysix1 = new Label(r.getFive_six() + " - " + r.getFive_six2());
            fiftysix1.setStyle("-fx-font: 14 Times New Roman;");
            fiftysix1.setStyle("-fx-font-weight: bold");
            fiftysix1.setLayoutX(175);
            fiftysix1.setLayoutY(150);
            sixtyone1 = new Label(r.getSix_one() + " - " + r.getSix_one2());
            sixtyone1.setStyle("-fx-font: 14 Times New Roman;");
            sixtyone1.setStyle("-fx-font-weight: bold");
            sixtyone1.setLayoutX(175);
            sixtyone1.setLayoutY(165);
            sixtytwo1 = new Label(r.getSix_two() + " - " + r.getSix_two2());
            sixtytwo1.setStyle("-fx-font: 14 Times New Roman;");
            sixtytwo1.setStyle("-fx-font-weight: bold");
            sixtytwo1.setLayoutX(175);
            sixtytwo1.setLayoutY(180);
            sixtythree1 = new Label(r.getSix_three() + " - " + r.getSix_three2());
            sixtythree1.setStyle("-fx-font: 14 Times New Roman;");
            sixtythree1.setStyle("-fx-font-weight: bold");
            sixtythree1.setLayoutX(175);
            sixtythree1.setLayoutY(195);
            sixtyfour1 = new Label(r.getSix_four() + " - " + r.getSix_four2());
            sixtyfour1.setStyle("-fx-font: 14 Times New Roman;");
            sixtyfour1.setStyle("-fx-font-weight: bold");
            sixtyfour1.setLayoutX(175);
            sixtyfour1.setLayoutY(210);
            sixtyfive1 = new Label(r.getSix_five() + " - " + r.getSix_five2());
            sixtyfive1.setStyle("-fx-font: 14 Times New Roman;");
            sixtyfive1.setStyle("-fx-font-weight: bold");
            sixtyfive1.setLayoutX(175);
            sixtyfive1.setLayoutY(225);
            sixtysix1 = new Label(r.getSix_six() + " - " + r.getSix_six2());
            sixtysix1.setStyle("-fx-font: 14 Times New Roman;");
            sixtysix1.setStyle("-fx-font-weight: bold");
            sixtysix1.setLayoutX(175);
            sixtysix1.setLayoutY(240);
        } else {
            eleven1 = new Label(r.getOne_one());
            eleven1.setStyle("-fx-font: 14 Times New Roman;");
            eleven1.setStyle("-fx-font-weight: bold");
            eleven1.setLayoutX(25);
            eleven1.setLayoutY(75);
            twelve1 = new Label(r.getOne_two());
            twelve1.setStyle("-fx-font: 14 Times New Roman;");
            twelve1.setStyle("-fx-font-weight: bold");
            twelve1.setLayoutX(25);
            twelve1.setLayoutY(90);
            thirteen1 = new Label(r.getOne_three());
            thirteen1.setStyle("-fx-font: 14 Times New Roman;");
            thirteen1.setStyle("-fx-font-weight: bold");
            thirteen1.setLayoutX(25);
            thirteen1.setLayoutY(105);
            fourteen1 = new Label(r.getOne_four());
            fourteen1.setStyle("-fx-font: 14 Times New Roman;");
            fourteen1.setStyle("-fx-font-weight: bold");
            fourteen1.setLayoutX(25);
            fourteen1.setLayoutY(120);
            fifteen1 = new Label(r.getOne_five());
            fifteen1.setStyle("-fx-font: 14 Times New Roman;");
            fifteen1.setStyle("-fx-font-weight: bold");
            fifteen1.setLayoutX(25);
            fifteen1.setLayoutY(135);
            sixteen1 = new Label(r.getOne_six());
            sixteen1.setStyle("-fx-font: 14 Times New Roman;");
            sixteen1.setStyle("-fx-font-weight: bold");
            sixteen1.setLayoutX(25);
            sixteen1.setLayoutY(150);
            twentyone1 = new Label(r.getTwo_one());
            twentyone1.setStyle("-fx-font: 14 Times New Roman;");
            twentyone1.setStyle("-fx-font-weight: bold");
            twentyone1.setLayoutX(25);
            twentyone1.setLayoutY(165);
            twentytwo1 = new Label(r.getTwo_two());
            twentytwo1.setStyle("-fx-font: 14 Times New Roman;");
            twentytwo1.setStyle("-fx-font-weight: bold");
            twentytwo1.setLayoutX(25);
            twentytwo1.setLayoutY(180);
            twentythree1 = new Label(r.getTwo_three());
            twentythree1.setStyle("-fx-font: 14 Times New Roman;");
            twentythree1.setStyle("-fx-font-weight: bold");
            twentythree1.setLayoutX(25);
            twentythree1.setLayoutY(195);
            twentyfour1 = new Label(r.getTwo_four());
            twentyfour1.setStyle("-fx-font: 14 Times New Roman;");
            twentyfour1.setStyle("-fx-font-weight: bold");
            twentyfour1.setLayoutX(25);
            twentyfour1.setLayoutY(210);
            twentyfive1 = new Label(r.getTwo_five());
            twentyfive1.setStyle("-fx-font: 14 Times New Roman;");
            twentyfive1.setStyle("-fx-font-weight: bold");
            twentyfive1.setLayoutX(25);
            twentyfive1.setLayoutY(225);
            twentysix1 = new Label(r.getTwo_six());
            twentysix1.setStyle("-fx-font: 14 Times New Roman;");
            twentysix1.setStyle("-fx-font-weight: bold");
            twentysix1.setLayoutX(25);
            twentysix1.setLayoutY(240);

            thirtyone1 = new Label(r.getThree_one());
            thirtyone1.setStyle("-fx-font: 14 Times New Roman;");
            thirtyone1.setStyle("-fx-font-weight: bold");
            thirtyone1.setLayoutX(100);
            thirtyone1.setLayoutY(75);
            thirtytwo1 = new Label(r.getThree_two());
            thirtytwo1.setStyle("-fx-font: 14 Times New Roman;");
            thirtytwo1.setStyle("-fx-font-weight: bold");
            thirtytwo1.setLayoutX(100);
            thirtytwo1.setLayoutY(90);
            thirtythree1 = new Label(r.getThree_three());
            thirtythree1.setStyle("-fx-font: 14 Times New Roman;");
            thirtythree1.setStyle("-fx-font-weight: bold");
            thirtythree1.setLayoutX(100);
            thirtythree1.setLayoutY(105);
            thirtyfour1 = new Label(r.getThree_four());
            thirtyfour1.setStyle("-fx-font: 14 Times New Roman;");
            thirtyfour1.setStyle("-fx-font-weight: bold");
            thirtyfour1.setLayoutX(100);
            thirtyfour1.setLayoutY(120);
            thirtyfive1 = new Label(r.getThree_five());
            thirtyfive1.setStyle("-fx-font: 14 Times New Roman;");
            thirtyfive1.setStyle("-fx-font-weight: bold");
            thirtyfive1.setLayoutX(100);
            thirtyfive1.setLayoutY(135);
            thirtysix1 = new Label(r.getThree_six());
            thirtysix1.setStyle("-fx-font: 14 Times New Roman;");
            thirtysix1.setStyle("-fx-font-weight: bold");
            thirtysix1.setLayoutX(100);
            thirtysix1.setLayoutY(150);
            fourtyone1 = new Label(r.getFour_one());
            fourtyone1.setStyle("-fx-font: 14 Times New Roman;");
            fourtyone1.setStyle("-fx-font-weight: bold");
            fourtyone1.setLayoutX(100);
            fourtyone1.setLayoutY(165);
            fourtytwo1 = new Label(r.getFour_two());
            fourtytwo1.setStyle("-fx-font: 14 Times New Roman;");
            fourtytwo1.setStyle("-fx-font-weight: bold");
            fourtytwo1.setLayoutX(100);
            fourtytwo1.setLayoutY(180);
            fourtythree1 = new Label(r.getFour_three());
            fourtythree1.setStyle("-fx-font: 14 Times New Roman;");
            fourtythree1.setStyle("-fx-font-weight: bold");
            fourtythree1.setLayoutX(100);
            fourtythree1.setLayoutY(195);
            fourtyfour1 = new Label(r.getFour_four());
            fourtyfour1.setStyle("-fx-font: 14 Times New Roman;");
            fourtyfour1.setStyle("-fx-font-weight: bold");
            fourtyfour1.setLayoutX(100);
            fourtyfour1.setLayoutY(210);
            fourtyfive1 = new Label(r.getFour_five());
            fourtyfive1.setStyle("-fx-font: 14 Times New Roman;");
            fourtyfive1.setStyle("-fx-font-weight: bold");
            fourtyfive1.setLayoutX(100);
            fourtyfive1.setLayoutY(225);
            fourtysix1 = new Label(r.getFour_six());
            fourtysix1.setStyle("-fx-font: 14 Times New Roman;");
            fourtysix1.setStyle("-fx-font-weight: bold");
            fourtysix1.setLayoutX(100);
            fourtysix1.setLayoutY(240);

            fiftyone1 = new Label(r.getFive_one());
            fiftyone1.setStyle("-fx-font: 14 Times New Roman;");
            fiftyone1.setStyle("-fx-font-weight: bold");
            fiftyone1.setLayoutX(175);
            fiftyone1.setLayoutY(75);
            fiftytwo1 = new Label(r.getFive_two());
            fiftytwo1.setStyle("-fx-font: 14 Times New Roman;");
            fiftytwo1.setStyle("-fx-font-weight: bold");
            fiftytwo1.setLayoutX(175);
            fiftytwo1.setLayoutY(90);
            fiftythree1 = new Label(r.getFive_three());
            fiftythree1.setStyle("-fx-font: 14 Times New Roman;");
            fiftythree1.setStyle("-fx-font-weight: bold");
            fiftythree1.setLayoutX(175);
            fiftythree1.setLayoutY(105);
            fiftyfour1 = new Label(r.getFive_four());
            fiftyfour1.setStyle("-fx-font: 14 Times New Roman;");
            fiftyfour1.setStyle("-fx-font-weight: bold");
            fiftyfour1.setLayoutX(175);
            fiftyfour1.setLayoutY(120);
            fiftyfive1 = new Label(r.getFive_five());
            fiftyfive1.setStyle("-fx-font: 14 Times New Roman;");
            fiftyfive1.setStyle("-fx-font-weight: bold");
            fiftyfive1.setLayoutX(175);
            fiftyfive1.setLayoutY(135);
            fiftysix1 = new Label(r.getFive_six());
            fiftysix1.setStyle("-fx-font: 14 Times New Roman;");
            fiftysix1.setStyle("-fx-font-weight: bold");
            fiftysix1.setLayoutX(175);
            fiftysix1.setLayoutY(150);
            sixtyone1 = new Label(r.getSix_one());
            sixtyone1.setStyle("-fx-font: 14 Times New Roman;");
            sixtyone1.setStyle("-fx-font-weight: bold");
            sixtyone1.setLayoutX(175);
            sixtyone1.setLayoutY(165);
            sixtytwo1 = new Label(r.getSix_two());
            sixtytwo1.setStyle("-fx-font: 14 Times New Roman;");
            sixtytwo1.setStyle("-fx-font-weight: bold");
            sixtytwo1.setLayoutX(175);
            sixtytwo1.setLayoutY(180);
            sixtythree1 = new Label(r.getSix_three());
            sixtythree1.setStyle("-fx-font: 14 Times New Roman;");
            sixtythree1.setStyle("-fx-font-weight: bold");
            sixtythree1.setLayoutX(175);
            sixtythree1.setLayoutY(195);
            sixtyfour1 = new Label(r.getSix_four());
            sixtyfour1.setStyle("-fx-font: 14 Times New Roman;");
            sixtyfour1.setStyle("-fx-font-weight: bold");
            sixtyfour1.setLayoutX(175);
            sixtyfour1.setLayoutY(210);
            sixtyfive1 = new Label(r.getSix_five());
            sixtyfive1.setStyle("-fx-font: 14 Times New Roman;");
            sixtyfive1.setStyle("-fx-font-weight: bold");
            sixtyfive1.setLayoutX(175);
            sixtyfive1.setLayoutY(225);
            sixtysix1 = new Label(r.getSix_six());
            sixtysix1.setStyle("-fx-font: 14 Times New Roman;");
            sixtysix1.setStyle("-fx-font-weight: bold");
            sixtysix1.setLayoutX(175);
            sixtysix1.setLayoutY(240);
        }

        eleven1.setTextFill(Color.web("#DC143C"));
        twelve1.setTextFill(Color.web("#DC143C"));
        thirteen1.setTextFill(Color.web("#DC143C"));
        fourteen1.setTextFill(Color.web("#DC143C"));
        fifteen1.setTextFill(Color.web("#DC143C"));
        sixteen1.setTextFill(Color.web("#DC143C"));
        twentyone1.setTextFill(Color.web("#DC143C"));
        twentytwo1.setTextFill(Color.web("#DC143C"));
        twentythree1.setTextFill(Color.web("#DC143C"));
        twentyfour1.setTextFill(Color.web("#DC143C"));
        twentyfive1.setTextFill(Color.web("#DC143C"));
        twentysix1.setTextFill(Color.web("#DC143C"));
        thirtyone1.setTextFill(Color.web("#DC143C"));
        thirtytwo1.setTextFill(Color.web("#DC143C"));
        thirtythree1.setTextFill(Color.web("#DC143C"));
        thirtyfour1.setTextFill(Color.web("#DC143C"));
        thirtyfive1.setTextFill(Color.web("#DC143C"));
        thirtysix1.setTextFill(Color.web("#DC143C"));
        fourtyone1.setTextFill(Color.web("#DC143C"));
        fourtytwo1.setTextFill(Color.web("#DC143C"));
        fourtythree1.setTextFill(Color.web("#DC143C"));
        fourtyfour1.setTextFill(Color.web("#DC143C"));
        fourtyfive1.setTextFill(Color.web("#DC143C"));
        fourtysix1.setTextFill(Color.web("#DC143C"));
        fiftyone1.setTextFill(Color.web("#DC143C"));
        fiftytwo1.setTextFill(Color.web("#DC143C"));
        fiftythree1.setTextFill(Color.web("#DC143C"));
        fiftyfour1.setTextFill(Color.web("#DC143C"));
        fiftyfive1.setTextFill(Color.web("#DC143C"));
        fiftysix1.setTextFill(Color.web("#DC143C"));
        sixtyone1.setTextFill(Color.web("#DC143C"));
        sixtytwo1.setTextFill(Color.web("#DC143C"));
        sixtythree1.setTextFill(Color.web("#DC143C"));
        sixtyfour1.setTextFill(Color.web("#DC143C"));
        sixtyfive1.setTextFill(Color.web("#DC143C"));
        sixtysix1.setTextFill(Color.web("#DC143C"));

        comp.getChildren().add(eleven);
        comp.getChildren().add(twelve);
        comp.getChildren().add(thirteen);
        comp.getChildren().add(fourteen);
        comp.getChildren().add(fifteen);
        comp.getChildren().add(sixteen);
        comp.getChildren().add(twentyone);
        comp.getChildren().add(twentytwo);
        comp.getChildren().add(twentythree);
        comp.getChildren().add(twentyfour);
        comp.getChildren().add(twentyfive);
        comp.getChildren().add(twentysix);
        comp.getChildren().add(thirtyone);
        comp.getChildren().add(thirtytwo);
        comp.getChildren().add(thirtythree);
        comp.getChildren().add(thirtyfour);
        comp.getChildren().add(thirtyfive);
        comp.getChildren().add(thirtysix);
        comp.getChildren().add(fourtyone);
        comp.getChildren().add(fourtytwo);
        comp.getChildren().add(fourtythree);
        comp.getChildren().add(fourtyfour);
        comp.getChildren().add(fourtyfive);
        comp.getChildren().add(fourtysix);
        comp.getChildren().add(fiftyone);
        comp.getChildren().add(fiftytwo);
        comp.getChildren().add(fiftythree);
        comp.getChildren().add(fiftyfour);
        comp.getChildren().add(fiftyfive);
        comp.getChildren().add(fiftysix);
        comp.getChildren().add(sixtyone);
        comp.getChildren().add(sixtytwo);
        comp.getChildren().add(sixtythree);
        comp.getChildren().add(sixtyfour);
        comp.getChildren().add(sixtyfive);
        comp.getChildren().add(sixtysix);

        comp.getChildren().add(eleven1);
        comp.getChildren().add(twelve1);
        comp.getChildren().add(thirteen1);
        comp.getChildren().add(fourteen1);
        comp.getChildren().add(fifteen1);
        comp.getChildren().add(sixteen1);
        comp.getChildren().add(twentyone1);
        comp.getChildren().add(twentytwo1);
        comp.getChildren().add(twentythree1);
        comp.getChildren().add(twentyfour1);
        comp.getChildren().add(twentyfive1);
        comp.getChildren().add(twentysix1);
        comp.getChildren().add(thirtyone1);
        comp.getChildren().add(thirtytwo1);
        comp.getChildren().add(thirtythree1);
        comp.getChildren().add(thirtyfour1);
        comp.getChildren().add(thirtyfive1);
        comp.getChildren().add(thirtysix1);
        comp.getChildren().add(fourtyone1);
        comp.getChildren().add(fourtytwo1);
        comp.getChildren().add(fourtythree1);
        comp.getChildren().add(fourtyfour1);
        comp.getChildren().add(fourtyfive1);
        comp.getChildren().add(fourtysix1);
        comp.getChildren().add(fiftyone1);
        comp.getChildren().add(fiftytwo1);
        comp.getChildren().add(fiftythree1);
        comp.getChildren().add(fiftyfour1);
        comp.getChildren().add(fiftyfive1);
        comp.getChildren().add(fiftysix1);
        comp.getChildren().add(sixtyone1);
        comp.getChildren().add(sixtytwo1);
        comp.getChildren().add(sixtythree1);
        comp.getChildren().add(sixtyfour1);
        comp.getChildren().add(sixtyfive1);
        comp.getChildren().add(sixtysix1);
    }

    private void checkPos(Player r, Label pos, Label throwHand, Label bats, String position) {
        if(r.getSpeed().equalsIgnoreCase("N")) {
            if(position.equalsIgnoreCase("1B")) {
                checkBats(r, bats);
                pos.setText("Firstbaseman (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("2B")) {
                checkBats(r, bats);
                pos.setText("Secondbaseman (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("SS")) {
                checkBats(r, bats);
                pos.setText("Shortstop (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("3B")) {
                checkBats(r, bats);
                pos.setText("Thirdbaseman (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("OF")) {
                checkBats(r, bats);
                pos.setText("Outfielder (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("C")) {
                checkBats(r, bats);
                pos.setText("Catcher (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("P")) {
                checkThrowHand(r, throwHand, bats);
                if(r.getPitching_rating().equalsIgnoreCase("W")) {
                    pos.setText("Grade " + r.getPitching_grade() + " Pitcher (" + r.getFielding_points()
                            + ") (W)");
                } else if(r.getPitching_rating().equalsIgnoreCase("X")) {
                    pos.setText("Grade " + r.getPitching_grade() + " Pitcher (" + r.getFielding_points()
                            + ") (X)");
                } else if(r.getPitching_rating().equalsIgnoreCase("Y")) {
                    pos.setText("Grade " + r.getPitching_grade() + " Pitcher (" + r.getFielding_points()
                            + ") (Y)");
                } else if(r.getPitching_rating().equalsIgnoreCase("Z")) {
                    pos.setText("Grade " + r.getPitching_grade() + " Pitcher (" + r.getFielding_points()
                            + ") (Z)");
                } else {
                    pos.setText("Grade " + r.getPitching_grade() + " Pitcher (" + r.getFielding_points()
                            + ")");
                }
            }
        } else {
            if(position.equalsIgnoreCase("1B")) {
                checkBats(r, bats);
                pos.setText("(" + r.getSpeed() + ") Firstbaseman (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("2B")) {
                checkBats(r, bats);
                pos.setText("(" + r.getSpeed() + ") Secondbaseman (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("SS")) {
                checkBats(r, bats);
                pos.setText("(" + r.getSpeed() + ") Shortstop (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("3B")) {
                checkBats(r, bats);
                pos.setText("(" + r.getSpeed() + ") Thirdbaseman (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("OF")) {
                checkBats(r, bats);
                pos.setText("(" + r.getSpeed() + ") Outfielder (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("C")) {
                checkBats(r, bats);
                pos.setText("(" + r.getSpeed() + ") Catcher (" + r.getFielding_points() + ")");
            } else if (position.equalsIgnoreCase("P")) {
                checkThrowHand(r, throwHand, bats);
                if(r.getPitching_rating().equalsIgnoreCase("W")) {
                    pos.setText("(" + r.getSpeed() + ") Grade " + r.getPitching_grade() + " Pitcher (" + r.getFielding_points()
                            + ") (W)");
                } else if(r.getPitching_rating().equalsIgnoreCase("X")) {
                    pos.setText("(" + r.getSpeed() + ") Grade " + r.getPitching_grade() + " Pitcher (" + r.getFielding_points()
                            + ") (X)");
                } else if(r.getPitching_rating().equalsIgnoreCase("Y")) {
                    pos.setText("(" + r.getSpeed() + ") Grade " + r.getPitching_grade() + " Pitcher (" + r.getFielding_points()
                            + ") (Y)");
                } else if(r.getPitching_rating().equalsIgnoreCase("Z")) {
                    pos.setText("(" + r.getSpeed() + ") Grade " + r.getPitching_grade() + " Pitcher (" + r.getFielding_points()
                            + ") (Z)");
                } else {
                    pos.setText("(" + r.getSpeed() + ") Grade " + r.getPitching_grade() + " Pitcher (" + r.getFielding_points()
                            + ")");
                }
            }
        }
    }

    private void checkBats(Player r, Label bats) {
        bats.setStyle("-fx-font: 12 arial;");
        AnchorPane.setLeftAnchor(bats, 0.0);
        AnchorPane.setRightAnchor(bats, 0.0);
        bats.setAlignment(Pos.CENTER);
        bats.setLayoutY(60);
        if(r.getBat_hand().equalsIgnoreCase("R")) {
            bats.setText("Bats: Right");
        } else if(r.getBat_hand().equalsIgnoreCase("L")) {
            bats.setText("Bats: Left");
        } else {
            bats.setText("Bats: Both");
        }
    }

    private void checkThrowHand(Player r, Label throwHand, Label bats) {
        throwHand.setStyle("-fx-font: 12 arial;");
        throwHand.setLayoutY(60);
        throwHand.setLayoutX(105);
        bats.setStyle("-fx-font: 12 arial;");
        bats.setLayoutY(60);
        bats.setLayoutX(43);
        if(r.getPitching_hand().equalsIgnoreCase("R")) {
            throwHand.setText("Throws: Right");

        } else if(r.getPitching_hand().equalsIgnoreCase("L")) {
            throwHand.setText("Throws: Left");
        } else {
            throwHand.setText("Throws: Both");
        }
        if(r.getBat_hand().equalsIgnoreCase("R")) {
            bats.setText("Bats: Right");
        } else if(r.getBat_hand().equalsIgnoreCase("L")) {
            bats.setText("Bats: Left");
        } else {
            bats.setText("Bats: Both");
        }
    }
}
