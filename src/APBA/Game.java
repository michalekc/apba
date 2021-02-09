package APBA;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Game {

    private Stage gameStage;
    private Button roll;
    private int[] rollOutcome;

    private int out = 0;
    private int strike = 0;
    private int ball = 0;
    private int inning = 1;
    private int playerAtBatAway = 0;
    private int playerAtBatHome = 0;
    private int teamAtBat = 0;
    private int awayFieldingGrade = 0;
    private int homeFieldingGrade = 0;
    private int rollAgain = 0;
    private int awayScore = 0;
    private int homeScore = 0;
    private int inningTopOrBot = 0;

    private String awayPitcherGrade;
    private String homePitcherGrade;
    private String awayPitcherRating;
    private String homePitcherRating;

    private String firstBaseRunnerSpeed;
    private String secondBaseRunnerSpeed;
    private String thirdBaseRunnerSpeed;

    private String outcomeDescription;
    private String awayLog = "AWAY OUTCOMES: ";
    private String homeLog = "HOME OUTCOMES: ";

    private boolean endedNaturally = false;

    private Label homeScoreLabel = new Label("0");
    private Label awayScoreLabel = new Label("0");
    private Label outs = new Label("Outs: ");
    private Label strikes = new Label("Strikes: ");
    private Label balls = new Label("Balls: ");
    private Label inningLabel = new Label("Inning: ");
    private Label upToBat = new Label("At Bat: ");
    private Label numberOnCard = new Label("Batter Outcome: ");
    private Label outsNum = new Label("0");
    private Label strikesNum = new Label("0");
    private Label ballsNum = new Label("0");
    private Label atBatPlayer = new Label("");
    private Label numOnCard = new Label("");
    private Label inningNum = new Label("Top 1");

    private Image redDice1 = new Image(getClass().getResourceAsStream("Icons/reddice1.png"));
    private Image redDice2 = new Image(getClass().getResourceAsStream("Icons/reddice2.png"));
    private Image redDice3 = new Image(getClass().getResourceAsStream("Icons/reddice3.png"));
    private Image redDice4 = new Image(getClass().getResourceAsStream("Icons/reddice4.png"));
    private Image redDice5 = new Image(getClass().getResourceAsStream("Icons/reddice5.png"));
    private Image redDice6 = new Image(getClass().getResourceAsStream("Icons/reddice6.png"));
    private Image whiteDice1 = new Image(getClass().getResourceAsStream("Icons/whitedice1.png"));
    private Image whiteDice2 = new Image(getClass().getResourceAsStream("Icons/whitedice2.png"));
    private Image whiteDice3 = new Image(getClass().getResourceAsStream("Icons/whitedice3.png"));
    private Image whiteDice4 = new Image(getClass().getResourceAsStream("Icons/whitedice4.png"));
    private Image whiteDice5 = new Image(getClass().getResourceAsStream("Icons/whitedice5.png"));
    private Image whiteDice6 = new Image(getClass().getResourceAsStream("Icons/whitedice6.png"));
    private Image apbaLogo = new Image(getClass().getResourceAsStream("Logos/apbalogo.png"));

    private ImageView redDie1 = new ImageView(redDice1);
    private ImageView redDie2 = new ImageView(redDice2);
    private ImageView redDie3 = new ImageView(redDice3);
    private ImageView redDie4 = new ImageView(redDice4);
    private ImageView redDie5 = new ImageView(redDice5);
    private ImageView redDie6 = new ImageView(redDice6);
    private ImageView whiteDie1 = new ImageView(whiteDice1);
    private ImageView whiteDie2 = new ImageView(whiteDice2);
    private ImageView whiteDie3 = new ImageView(whiteDice3);
    private ImageView whiteDie4 = new ImageView(whiteDice4);
    private ImageView whiteDie5 = new ImageView(whiteDice5);
    private ImageView whiteDie6 = new ImageView(whiteDice6);

    private TextArea situationOutcome = new TextArea();

    private ArrayList<Player> home;
    private ArrayList<Player> away;
    private ImageView thirdBaseDot;
    private ImageView secondBaseDot;
    private ImageView firstBaseDot;

    public void startGame(ArrayList<Player> home, ArrayList<Player> away, ImageView thirdBaseDot,
                          ImageView secondBaseDot, ImageView firstBaseDot, String awayPitcherGrade,
                          String homePitcherGrade, String awayPitcherRating, String homePitcherRating,
                          int awayFieldingGrade, int homeFieldingGrade, Image awayImage, Image homeImage) {
        this.home = home;
        this.away = away;
        this.firstBaseDot = firstBaseDot;
        this.secondBaseDot = secondBaseDot;
        this.thirdBaseDot = thirdBaseDot;
        this.homePitcherGrade = homePitcherGrade;
        this.awayPitcherGrade = awayPitcherGrade;
        this.awayPitcherRating = awayPitcherRating;
        this.homePitcherRating = homePitcherRating;
        this.awayFieldingGrade = awayFieldingGrade;
        this.homeFieldingGrade = homeFieldingGrade;

        addInningToAwayLog();

        firstBaseDot.setVisible(false);
        secondBaseDot.setVisible(false);
        thirdBaseDot.setVisible(false);

        gameStage = new Stage();
        Pane comp = new Pane();
        gameStage.setWidth(516);
        gameStage.setHeight(400);

        roll = new Button("Roll");
        roll.setLayoutY(205);
        roll.setLayoutX(20);
        roll.setPrefWidth(88);
        roll.setPrefHeight(44);
        comp.getChildren().add(roll);

        roll.setOnAction(e -> {
            roll.setDisable(true);
            rollOutcome = roll();
            situationOutcome.clear();
            numOnCard.setText("");
            if(teamAtBat == 0) {
                atBatPlayer.setText(away.get(playerAtBatAway).getName());
            } else {
                atBatPlayer.setText(home.get(playerAtBatHome).getName());
            }
        });

        initDice();
        initLabels();

        situationOutcome.setPrefSize(495, 100);
        situationOutcome.setLayoutX(2);
        situationOutcome.setLayoutY(259);
        situationOutcome.setEditable(false);
        situationOutcome.setWrapText(true);

        comp.getChildren().add(situationOutcome);

        ImageView awayLogo = new ImageView(awayImage);
        ImageView homeLogo = new ImageView(homeImage);

        awayLogo.setFitWidth(90);
        awayLogo.setFitHeight(90);
        homeLogo.setFitWidth(90);
        homeLogo.setFitHeight(90);
        awayLogo.setLayoutX(300);
        homeLogo.setLayoutX(300);
        awayLogo.setLayoutY(15);
        homeLogo.setLayoutY(115);

        homeScoreLabel.setLayoutX(410);
        awayScoreLabel.setLayoutX(410);
        homeScoreLabel.setLayoutY(115);
        awayScoreLabel.setLayoutY(15);

        comp.getChildren().add(awayLogo);
        comp.getChildren().add(homeLogo);
        comp.getChildren().add(homeScoreLabel);
        comp.getChildren().add(awayScoreLabel);

        comp.getChildren().add(inningLabel);
        comp.getChildren().add(inningNum);
        comp.getChildren().add(upToBat);
        comp.getChildren().add(atBatPlayer);
        comp.getChildren().add(outs);
        comp.getChildren().add(strikes);
        comp.getChildren().add(balls);
        comp.getChildren().add(outsNum);
        comp.getChildren().add(strikesNum);
        comp.getChildren().add(ballsNum);
        comp.getChildren().add(numberOnCard);
        comp.getChildren().add(numOnCard);

        comp.getChildren().add(redDie1);
        comp.getChildren().add(redDie2);
        comp.getChildren().add(redDie3);
        comp.getChildren().add(redDie4);
        comp.getChildren().add(redDie5);
        comp.getChildren().add(redDie6);
        comp.getChildren().add(whiteDie1);
        comp.getChildren().add(whiteDie2);
        comp.getChildren().add(whiteDie3);
        comp.getChildren().add(whiteDie4);
        comp.getChildren().add(whiteDie5);
        comp.getChildren().add(whiteDie6);

        gameStage.setTitle("APBA Baseball");

        Scene stageScene = new Scene(comp, 516, 400);
        gameStage.setScene(stageScene);
        gameStage.getIcons().add(apbaLogo);
        gameStage.show();
    }

    private void initDice() {
        redDie1.setVisible(true);
        redDie2.setVisible(false);
        redDie3.setVisible(false);
        redDie4.setVisible(false);
        redDie5.setVisible(false);
        redDie6.setVisible(false);
        whiteDie1.setVisible(true);
        whiteDie2.setVisible(false);
        whiteDie3.setVisible(false);
        whiteDie4.setVisible(false);
        whiteDie5.setVisible(false);
        whiteDie6.setVisible(false);

        redDie1.setFitHeight(50);
        redDie1.setFitWidth(50);
        redDie2.setFitHeight(50);
        redDie2.setFitWidth(50);
        redDie3.setFitHeight(50);
        redDie3.setFitWidth(50);
        redDie4.setFitHeight(50);
        redDie4.setFitWidth(50);
        redDie5.setFitHeight(50);
        redDie5.setFitWidth(50);
        redDie6.setFitHeight(50);
        redDie6.setFitWidth(50);

        redDie1.setLayoutX(125);
        redDie2.setLayoutX(125);
        redDie3.setLayoutX(125);
        redDie4.setLayoutX(125);
        redDie5.setLayoutX(125);
        redDie6.setLayoutX(125);
        redDie1.setLayoutY(200);
        redDie2.setLayoutY(200);
        redDie3.setLayoutY(200);
        redDie4.setLayoutY(200);
        redDie5.setLayoutY(200);
        redDie6.setLayoutY(200);

        whiteDie1.setFitHeight(50);
        whiteDie1.setFitWidth(50);
        whiteDie2.setFitHeight(50);
        whiteDie2.setFitWidth(50);
        whiteDie3.setFitHeight(50);
        whiteDie3.setFitWidth(50);
        whiteDie4.setFitHeight(50);
        whiteDie4.setFitWidth(50);
        whiteDie5.setFitHeight(50);
        whiteDie5.setFitWidth(50);
        whiteDie6.setFitHeight(50);
        whiteDie6.setFitWidth(50);

        whiteDie1.setLayoutX(185);
        whiteDie2.setLayoutX(185);
        whiteDie3.setLayoutX(185);
        whiteDie4.setLayoutX(185);
        whiteDie5.setLayoutX(185);
        whiteDie6.setLayoutX(185);
        whiteDie1.setLayoutY(200);
        whiteDie2.setLayoutY(200);
        whiteDie3.setLayoutY(200);
        whiteDie4.setLayoutY(200);
        whiteDie5.setLayoutY(200);
        whiteDie6.setLayoutY(200);
    }

    private void initLabels() {
        inningLabel.setPrefWidth(100);
        inningLabel.setLayoutX(15);
        inningNum.setLayoutX(90);

        upToBat.setPrefWidth(100);
        upToBat.setLayoutX(15);
        upToBat.setLayoutY(25);
        atBatPlayer.setLayoutY(29);
        atBatPlayer.setLayoutX(90);

        numberOnCard.setPrefWidth(200);
        numberOnCard.setLayoutX(15);
        numberOnCard.setLayoutY(50);
        numOnCard.setLayoutY(50);
        numOnCard.setLayoutX(193);

        strikes.setPrefWidth(100);
        strikes.setLayoutX(15);
        strikes.setLayoutY(120);
        strikesNum.setLayoutY(120);
        strikesNum.setLayoutX(97);

        balls.setPrefWidth(100);
        balls.setLayoutX(36);
        balls.setLayoutY(95);
        ballsNum.setLayoutY(95);
        ballsNum.setLayoutX(97);

        outs.setPrefWidth(100);
        outs.setLayoutX(37);
        outs.setLayoutY(145);
        outsNum.setLayoutY(145);
        outsNum.setLayoutX(97);

        inningLabel.setStyle("-fx-font: 24 arial;");
        inningNum.setStyle("-fx-font: 24 arial;");
        upToBat.setStyle("-fx-font: 24 arial;");
        atBatPlayer.setStyle("-fx-font: 18 arial;");
        numberOnCard.setStyle("-fx-font: 24 arial;");
        numOnCard.setStyle("-fx-font: 24 arial;");
        balls.setStyle("-fx-font: 24 arial;");
        ballsNum.setStyle("-fx-font: 24 arial;");
        strikes.setStyle("-fx-font: 24 arial;");
        strikesNum.setStyle("-fx-font: 24 arial;");
        outs.setStyle("-fx-font: 24 arial;");
        outsNum.setStyle("-fx-font: 24 arial;");
        homeScoreLabel.setStyle("-fx-font: 80 arial;");
        awayScoreLabel.setStyle("-fx-font: 80 arial;");
    }

    private void printAwayNum() {
        if(rollOutcome[0] == 1 && rollOutcome[1] == 1) {
            if(away.get(playerAtBatAway).getOne_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getOne_one());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_one()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 2) {
            if(away.get(playerAtBatAway).getOne_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getOne_two());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_two()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 3) {
            if(away.get(playerAtBatAway).getOne_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getOne_three());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_three()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 4) {
            if(away.get(playerAtBatAway).getOne_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getOne_four());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_four()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 5) {
            if(away.get(playerAtBatAway).getOne_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getOne_five());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_five()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 6) {
            if(away.get(playerAtBatAway).getOne_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getOne_six());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_six()));
                addToAwayLog();
            }
        }

        if(rollOutcome[0] == 2 && rollOutcome[1] == 1) {
            if(away.get(playerAtBatAway).getTwo_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getTwo_one());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_one()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 2) {
            if(away.get(playerAtBatAway).getTwo_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getTwo_two());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_two()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 3) {
            if(away.get(playerAtBatAway).getTwo_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getTwo_three());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_three()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 4) {
            if(away.get(playerAtBatAway).getTwo_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getTwo_four());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_four()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 5) {
            if(away.get(playerAtBatAway).getTwo_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getTwo_five());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_five()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 6) {
            if(away.get(playerAtBatAway).getTwo_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getTwo_six());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_six()));
                addToAwayLog();
            }
        }

        if(rollOutcome[0] == 3 && rollOutcome[1] == 1) {
            if(away.get(playerAtBatAway).getThree_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getThree_one());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_one()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 2) {
            if(away.get(playerAtBatAway).getThree_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getThree_two());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_two()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 3) {
            if(away.get(playerAtBatAway).getThree_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getThree_three());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_three()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 4) {
            if(away.get(playerAtBatAway).getThree_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getThree_four());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_four()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 5) {
            if(away.get(playerAtBatAway).getThree_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getThree_five());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_five()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 6) {
            if(away.get(playerAtBatAway).getThree_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getThree_six());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_six()));
                addToAwayLog();
            }
        }

        if(rollOutcome[0] == 4 && rollOutcome[1] == 1) {
            if(away.get(playerAtBatAway).getFour_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFour_one());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_one()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 2) {
            if(away.get(playerAtBatAway).getFour_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFour_two());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_two()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 3) {
            if(away.get(playerAtBatAway).getFour_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFour_three());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_three()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 4) {
            if(away.get(playerAtBatAway).getFour_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFour_four());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_four()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 5) {
            if(away.get(playerAtBatAway).getFour_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFour_five());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_five()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 6) {
            if(away.get(playerAtBatAway).getFour_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFour_six());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_six()));
                addToAwayLog();
            }
        }

        if(rollOutcome[0] == 5 && rollOutcome[1] == 1) {
            if(away.get(playerAtBatAway).getFive_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFive_one());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_one()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 2) {
            if(away.get(playerAtBatAway).getFive_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFive_two());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_two()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 3) {
            if(away.get(playerAtBatAway).getFive_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFive_three());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_three()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 4) {
            if(away.get(playerAtBatAway).getFive_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFive_four());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_four()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 5) {
            if(away.get(playerAtBatAway).getFive_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFive_five());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_five()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 6) {
            if(away.get(playerAtBatAway).getFive_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getFive_six());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_six()));
                addToAwayLog();
            }
        }

        if(rollOutcome[0] == 6 && rollOutcome[1] == 1) {
            if(away.get(playerAtBatAway).getSix_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getSix_one());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_one()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 2) {
            if(away.get(playerAtBatAway).getSix_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getSix_two());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_two()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 3) {
            if(away.get(playerAtBatAway).getSix_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getSix_three());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_three()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 4) {
            if(away.get(playerAtBatAway).getSix_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getSix_four());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_four()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 5) {
            if(away.get(playerAtBatAway).getSix_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getSix_five());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_five()));
                addToAwayLog();
            }
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 6) {
            if(away.get(playerAtBatAway).getSix_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatAway).getSix_six());
                getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_six()));
                addToAwayLog();
            }
        }
    }

    private void rollAgainAway() {
        if(rollOutcome[0] == 1 && rollOutcome[1] == 1) {
            numOnCard.setText(away.get(playerAtBatAway).getOne_one2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 2) {
            numOnCard.setText(away.get(playerAtBatAway).getOne_two2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 3) {
            numOnCard.setText(away.get(playerAtBatAway).getOne_three2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 4) {
            numOnCard.setText(away.get(playerAtBatAway).getOne_four2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 5) {
            numOnCard.setText(away.get(playerAtBatAway).getOne_five2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 6) {
            numOnCard.setText(away.get(playerAtBatAway).getOne_six2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getOne_six2()));
            rollAgain = 0;
        }

        if(rollOutcome[0] == 2 && rollOutcome[1] == 1) {
            numOnCard.setText(away.get(playerAtBatAway).getTwo_one2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 2) {
            numOnCard.setText(away.get(playerAtBatAway).getTwo_two2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 3) {
            numOnCard.setText(away.get(playerAtBatAway).getTwo_three2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 4) {
            numOnCard.setText(away.get(playerAtBatAway).getTwo_four2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 5) {
            numOnCard.setText(away.get(playerAtBatAway).getTwo_five2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 6) {
            numOnCard.setText(away.get(playerAtBatAway).getTwo_six2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getTwo_six2()));
            rollAgain = 0;
        }

        if(rollOutcome[0] == 3 && rollOutcome[1] == 1) {
            numOnCard.setText(away.get(playerAtBatAway).getThree_one2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 2) {
            numOnCard.setText(away.get(playerAtBatAway).getThree_two2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 3) {
            numOnCard.setText(away.get(playerAtBatAway).getThree_three2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 4) {
            numOnCard.setText(away.get(playerAtBatAway).getThree_four2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 5) {
            numOnCard.setText(away.get(playerAtBatAway).getThree_five2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 6) {
            numOnCard.setText(away.get(playerAtBatAway).getThree_six2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getThree_six2()));
            rollAgain = 0;
        }

        if(rollOutcome[0] == 4 && rollOutcome[1] == 1) {
            numOnCard.setText(away.get(playerAtBatAway).getFour_one2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 2) {
            numOnCard.setText(away.get(playerAtBatAway).getFour_two2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 3) {
            numOnCard.setText(away.get(playerAtBatAway).getFour_three2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 4) {
            numOnCard.setText(away.get(playerAtBatAway).getFour_four2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 5) {
            numOnCard.setText(away.get(playerAtBatAway).getFour_five2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 6) {
            numOnCard.setText(away.get(playerAtBatAway).getFour_six2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFour_six2()));
            rollAgain = 0;
        }

        if(rollOutcome[0] == 5 && rollOutcome[1] == 1) {
            numOnCard.setText(away.get(playerAtBatAway).getFive_one2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 2) {
            numOnCard.setText(away.get(playerAtBatAway).getFive_two2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 3) {
            numOnCard.setText(away.get(playerAtBatAway).getFive_three2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 4) {
            numOnCard.setText(away.get(playerAtBatAway).getFive_four2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 5) {
            numOnCard.setText(away.get(playerAtBatAway).getFive_five2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 6) {
            numOnCard.setText(away.get(playerAtBatAway).getFive_six2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getFive_six2()));
            rollAgain = 0;
        }

        if(rollOutcome[0] == 6 && rollOutcome[1] == 1) {
            numOnCard.setText(away.get(playerAtBatAway).getSix_one2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 2) {
            numOnCard.setText(away.get(playerAtBatAway).getSix_two2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 3) {
            numOnCard.setText(away.get(playerAtBatAway).getSix_three2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 4) {
            numOnCard.setText(away.get(playerAtBatAway).getSix_four2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 5) {
            numOnCard.setText(away.get(playerAtBatAway).getSix_five2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 6) {
            numOnCard.setText(away.get(playerAtBatAway).getSix_six2());
            getAwayOutcome(Integer.parseInt(away.get(playerAtBatAway).getSix_six2()));
            rollAgain = 0;
        }
        addToAwayLog();
    }

    private void printHomeNum() {
        if(rollOutcome[0] == 1 && rollOutcome[1] == 1) {
            if(home.get(playerAtBatHome).getOne_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getOne_one());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_one()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 2) {
            if(home.get(playerAtBatHome).getOne_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getOne_two());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_two()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 3) {
            if(home.get(playerAtBatHome).getOne_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getOne_three());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_three()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 4) {
            if(home.get(playerAtBatHome).getOne_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getOne_four());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_four()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 5) {
            if(home.get(playerAtBatHome).getOne_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getOne_five());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_five()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 6) {
            if(home.get(playerAtBatHome).getOne_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getOne_six());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_six()));
                addToHomeLog();
            }
        }

        if(rollOutcome[0] == 2 && rollOutcome[1] == 1) {
            if(home.get(playerAtBatHome).getTwo_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getTwo_one());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_one()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 2) {
            if(home.get(playerAtBatHome).getTwo_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getTwo_two());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_two()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 3) {
            if(home.get(playerAtBatHome).getTwo_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getTwo_three());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_three()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 4) {
            if(home.get(playerAtBatHome).getTwo_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getTwo_four());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_four()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 5) {
            if(home.get(playerAtBatHome).getTwo_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getTwo_five());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_five()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 6) {
            if(home.get(playerAtBatHome).getTwo_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getTwo_six());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_six()));
                addToHomeLog();
            }
        }

        if(rollOutcome[0] == 3 && rollOutcome[1] == 1) {
            if(home.get(playerAtBatHome).getThree_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getThree_one());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_one()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 2) {
            if(home.get(playerAtBatHome).getThree_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getThree_two());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_two()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 3) {
            if(home.get(playerAtBatHome).getThree_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getThree_three());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_three()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 4) {
            if(home.get(playerAtBatHome).getThree_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getThree_four());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_four()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 5) {
            if(home.get(playerAtBatHome).getThree_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getThree_five());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_five()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 6) {
            if(home.get(playerAtBatHome).getThree_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getThree_six());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_six()));
                addToHomeLog();
            }
        }

        if(rollOutcome[0] == 4 && rollOutcome[1] == 1) {
            if(home.get(playerAtBatHome).getFour_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getFour_one());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_one()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 2) {
            if(home.get(playerAtBatHome).getFour_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getFour_two());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_two()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 3) {
            if(home.get(playerAtBatHome).getFour_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getFour_three());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_three()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 4) {
            if(home.get(playerAtBatHome).getFour_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getFour_four());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_four()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 5) {
            if(home.get(playerAtBatHome).getFour_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getFour_five());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_five()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 6) {
            if(home.get(playerAtBatHome).getFour_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getFour_six());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_six()));
                addToHomeLog();
            }
        }

        if(rollOutcome[0] == 5 && rollOutcome[1] == 1) {
            if(home.get(playerAtBatHome).getFive_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getFive_one());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_one()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 2) {
            if(home.get(playerAtBatHome).getFive_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getFive_two());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_two()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 3) {
            if(home.get(playerAtBatHome).getFive_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getFive_three());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_three()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 4) {
            if(home.get(playerAtBatHome).getFive_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getFive_four());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_four()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 5) {
            if(home.get(playerAtBatHome).getFive_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getFive_five());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_five()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 6) {
            if(home.get(playerAtBatHome).getFive_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(away.get(playerAtBatHome).getFive_six());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_six()));
                addToHomeLog();
            }
        }

        if(rollOutcome[0] == 6 && rollOutcome[1] == 1) {
            if(home.get(playerAtBatHome).getSix_one().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getSix_one());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_one()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 2) {
            if(home.get(playerAtBatHome).getSix_two().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getSix_two());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_two()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 3) {
            if(home.get(playerAtBatHome).getSix_three().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getSix_three());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_three()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 4) {
            if(home.get(playerAtBatHome).getSix_four().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getSix_four());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_four()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 5) {
            if(home.get(playerAtBatHome).getSix_five().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getSix_five());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_five()));
                addToHomeLog();
            }
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 6) {
            if(home.get(playerAtBatHome).getSix_six().equalsIgnoreCase("0")) {
                numOnCard.setText("ROLL\nAGAIN");
                rollAgain = 1;
            } else {
                numOnCard.setText(home.get(playerAtBatHome).getSix_six());
                getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_six()));
                addToHomeLog();
            }
        }
    }

    private void rollAgainHome() {
        if(rollOutcome[0] == 1 && rollOutcome[1] == 1) {
            numOnCard.setText(home.get(playerAtBatHome).getOne_one2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 2) {
            numOnCard.setText(home.get(playerAtBatHome).getOne_two2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 3) {
            numOnCard.setText(home.get(playerAtBatHome).getOne_three2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 4) {
            numOnCard.setText(home.get(playerAtBatHome).getOne_four2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 5) {
            numOnCard.setText(home.get(playerAtBatHome).getOne_five2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 1 && rollOutcome[1] == 6) {
            numOnCard.setText(home.get(playerAtBatHome).getOne_six2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getOne_six2()));
            rollAgain = 0;
        }

        if(rollOutcome[0] == 2 && rollOutcome[1] == 1) {
            numOnCard.setText(home.get(playerAtBatHome).getTwo_one2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 2) {
            numOnCard.setText(home.get(playerAtBatHome).getTwo_two2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 3) {
            numOnCard.setText(home.get(playerAtBatHome).getTwo_three2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 4) {
            numOnCard.setText(home.get(playerAtBatHome).getTwo_four2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 5) {
            numOnCard.setText(home.get(playerAtBatHome).getTwo_five2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 2 && rollOutcome[1] == 6) {
            numOnCard.setText(home.get(playerAtBatHome).getTwo_six2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getTwo_six2()));
            rollAgain = 0;
        }

        if(rollOutcome[0] == 3 && rollOutcome[1] == 1) {
            numOnCard.setText(home.get(playerAtBatHome).getThree_one2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 2) {
            numOnCard.setText(home.get(playerAtBatHome).getThree_two2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 3) {
            numOnCard.setText(home.get(playerAtBatHome).getThree_three2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 4) {
            numOnCard.setText(home.get(playerAtBatHome).getThree_four2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 5) {
            numOnCard.setText(home.get(playerAtBatHome).getThree_five2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 3 && rollOutcome[1] == 6) {
            numOnCard.setText(home.get(playerAtBatHome).getThree_six2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getThree_six2()));
            rollAgain = 0;
        }

        if(rollOutcome[0] == 4 && rollOutcome[1] == 1) {
            numOnCard.setText(home.get(playerAtBatHome).getFour_one2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 2) {
            numOnCard.setText(home.get(playerAtBatHome).getFour_two2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 3) {
            numOnCard.setText(home.get(playerAtBatHome).getFour_three2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 4) {
            numOnCard.setText(home.get(playerAtBatHome).getFour_four2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 5) {
            numOnCard.setText(home.get(playerAtBatHome).getFour_five2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 4 && rollOutcome[1] == 6) {
            numOnCard.setText(home.get(playerAtBatHome).getFour_six2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFour_six2()));
            rollAgain = 0;
        }

        if(rollOutcome[0] == 5 && rollOutcome[1] == 1) {
            numOnCard.setText(home.get(playerAtBatHome).getFive_one2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 2) {
            numOnCard.setText(home.get(playerAtBatHome).getFive_two2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 3) {
            numOnCard.setText(home.get(playerAtBatHome).getFive_three2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 4) {
            numOnCard.setText(home.get(playerAtBatHome).getFive_four2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 5) {
            numOnCard.setText(home.get(playerAtBatHome).getFive_five2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 5 && rollOutcome[1] == 6) {
            numOnCard.setText(home.get(playerAtBatHome).getFive_six2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getFive_six2()));
            rollAgain = 0;
        }

        if(rollOutcome[0] == 6 && rollOutcome[1] == 1) {
            numOnCard.setText(home.get(playerAtBatHome).getSix_one2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_one2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 2) {
            numOnCard.setText(home.get(playerAtBatHome).getSix_two2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_two2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 3) {
            numOnCard.setText(home.get(playerAtBatHome).getSix_three2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_three2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 4) {
            numOnCard.setText(home.get(playerAtBatHome).getSix_four2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_four2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 5) {
            numOnCard.setText(home.get(playerAtBatHome).getSix_five2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_five2()));
            rollAgain = 0;
        }
        if(rollOutcome[0] == 6 && rollOutcome[1] == 6) {
            numOnCard.setText(home.get(playerAtBatHome).getSix_six2());
            getHomeOutcome(Integer.parseInt(home.get(playerAtBatHome).getSix_six2()));
            rollAgain = 0;
        }
        addToHomeLog();
    }

    private void getAwayOutcome(int playerNumber) {
        if(basesEmpty()) {
            if(playerNumber <= 11) {
                if(homePitcherGrade.equalsIgnoreCase("A")) {
                    gradeABasesEmpty(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("B")) {
                    gradeBBasesEmpty(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("C")) {
                    gradeCBasesEmpty(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("D")) {
                    gradeDBasesEmpty(playerNumber);
                }
            } else {
                if(homeFieldingGrade == 1) {
                    fieldingOneBasesEmpty(playerNumber);
                }
                if(homeFieldingGrade == 2) {
                    fieldingTwoBasesEmpty(playerNumber);
                }
                if(homeFieldingGrade == 3) {
                    fieldingThreeBasesEmpty(playerNumber);
                }
            }
        }

        else if(runnerOnFirst()) {
            if(playerNumber <= 11) {
                if(homePitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnFirst(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnFirst(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnFirst(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnFirst(playerNumber);
                }
            } else {
                if(homeFieldingGrade == 1) {
                    fieldingOneManOnFirst(playerNumber);
                }
                if(homeFieldingGrade == 2) {
                    fieldingTwoManOnFirst(playerNumber);
                }
                if(homeFieldingGrade == 3) {
                    fieldingThreeManOnFirst(playerNumber);
                }
            }
        }

        else if(runnerOnSecond()) {
            if(playerNumber <= 11) {
                if(homePitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnSecond(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnSecond(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnSecond(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnSecond(playerNumber);
                }
            } else {
                if(homeFieldingGrade == 1) {
                    fieldingOneManOnSecond(playerNumber);
                }
                if(homeFieldingGrade == 2) {
                    fieldingTwoManOnSecond(playerNumber);
                }
                if(homeFieldingGrade == 3) {
                    fieldingThreeManOnSecond(playerNumber);
                }
            }
        }

        else if(runnerOnThird()) {
            if(playerNumber <= 11) {
                if(homePitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnThird(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnThird(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnThird(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnThird(playerNumber);
                }
            } else {
                if(homeFieldingGrade == 1) {
                    fieldingOneManOnThird(playerNumber);
                }
                if(homeFieldingGrade == 2) {
                    fieldingTwoManOnThird(playerNumber);
                }
                if(homeFieldingGrade == 3) {
                    fieldingThreeManOnThird(playerNumber);
                }
            }
        }

        else if(runnerOnFirstAndSecond()) {
            if(playerNumber <= 11) {
                if(homePitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnFirstAndSecond(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnFirstAndSecond(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnFirstAndSecond(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnFirstAndSecond(playerNumber);
                }
            } else {
                if(homeFieldingGrade == 1) {
                    fieldingOneManOnFirstAndSecond(playerNumber);
                }
                if(homeFieldingGrade == 2) {
                    fieldingTwoManOnFirstAndSecond(playerNumber);
                }
                if(homeFieldingGrade == 3) {
                    fieldingThreeManOnFirstAndSecond(playerNumber);
                }
            }
        }

        else if(runnerOnFirstAndThird()) {
            if(playerNumber <= 11) {
                if(homePitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnFirstAndThird(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnFirstAndThird(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnFirstAndThird(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnFirstAndThird(playerNumber);
                }
            } else {
                if(homeFieldingGrade == 1) {
                    fieldingOneManOnFirstAndThird(playerNumber);
                }
                if(homeFieldingGrade == 2) {
                    fieldingTwoManOnFirstAndThird(playerNumber);
                }
                if(homeFieldingGrade == 3) {
                    fieldingThreeManOnFirstAndThird(playerNumber);
                }
            }
        }

        else if(runnerOnSecondAndThird()) {
            if(playerNumber <= 11) {
                if(homePitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnSecondAndThird(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnSecondAndThird(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnSecondAndThird(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnSecondAndThird(playerNumber);
                }
            } else {
                if(homeFieldingGrade == 1) {
                    fieldingOneManOnSecondAndThird(playerNumber);
                }
                if(homeFieldingGrade == 2) {
                    fieldingTwoManOnSecondAndThird(playerNumber);
                }
                if(homeFieldingGrade == 3) {
                    fieldingThreeManOnSecondAndThird(playerNumber);
                }
            }
        }

        else if(basesLoaded()) {
            if(playerNumber <= 11) {
                if(homePitcherGrade.equalsIgnoreCase("A")) {
                    gradeABasesLoaded(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("B")) {
                    gradeBBasesLoaded(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("C")) {
                    gradeCBasesLoaded(playerNumber);
                }
                if(homePitcherGrade.equalsIgnoreCase("D")) {
                    gradeDBasesLoaded(playerNumber);
                }
            } else {
                if(homeFieldingGrade == 1) {
                    fieldingOneBasesLoaded(playerNumber);
                }
                if(homeFieldingGrade == 2) {
                    fieldingTwoBasesLoaded(playerNumber);
                }
                if(homeFieldingGrade == 3) {
                    fieldingThreeBasesLoaded(playerNumber);
                }
            }
        }
    }

    private void getHomeOutcome(int playerNumber) {
        if(basesEmpty()) {
            if(playerNumber <= 11) {
                if(awayPitcherGrade.equalsIgnoreCase("A")) {
                    gradeABasesEmpty(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("B")) {
                    gradeBBasesEmpty(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("C")) {
                    gradeCBasesEmpty(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("D")) {
                    gradeDBasesEmpty(playerNumber);
                }
            } else {
                if(awayFieldingGrade == 1) {
                    fieldingOneBasesEmpty(playerNumber);
                }
                if(awayFieldingGrade == 2) {
                    fieldingTwoBasesEmpty(playerNumber);
                }
                if(awayFieldingGrade == 3) {
                    fieldingThreeBasesEmpty(playerNumber);
                }
            }
        }

         else if(runnerOnFirst()) {
            if(playerNumber <= 11) {
                if(awayPitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnFirst(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnFirst(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnFirst(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnFirst(playerNumber);
                }
            } else {
                if(awayFieldingGrade == 1) {
                    fieldingOneManOnFirst(playerNumber);
                }
                if(awayFieldingGrade == 2) {
                    fieldingTwoManOnFirst(playerNumber);
                }
                if(awayFieldingGrade == 3) {
                    fieldingThreeManOnFirst(playerNumber);
                }
            }
        }

        else if(runnerOnSecond()) {
            if(playerNumber <= 11) {
                if(awayPitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnSecond(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnSecond(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnSecond(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnSecond(playerNumber);
                }
            } else {
                if(awayFieldingGrade == 1) {
                    fieldingOneManOnSecond(playerNumber);
                }
                if(awayFieldingGrade == 2) {
                    fieldingTwoManOnSecond(playerNumber);
                }
                if(awayFieldingGrade == 3) {
                    fieldingThreeManOnSecond(playerNumber);
                }
            }
        }

        else if(runnerOnThird()) {
            if(playerNumber <= 11) {
                if(awayPitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnThird(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnThird(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnThird(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnThird(playerNumber);
                }
            } else {
                if(awayFieldingGrade == 1) {
                    fieldingOneManOnThird(playerNumber);
                }
                if(awayFieldingGrade == 2) {
                    fieldingTwoManOnThird(playerNumber);
                }
                if(awayFieldingGrade == 3) {
                    fieldingThreeManOnThird(playerNumber);
                }
            }
        }

        else if(runnerOnFirstAndSecond()) {
            if(playerNumber <= 11) {
                if(awayPitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnFirstAndSecond(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnFirstAndSecond(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnFirstAndSecond(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnFirstAndSecond(playerNumber);
                }
            } else {
                if(awayFieldingGrade == 1) {
                    fieldingOneManOnFirstAndSecond(playerNumber);
                }
                if(awayFieldingGrade == 2) {
                    fieldingTwoManOnFirstAndSecond(playerNumber);
                }
                if(awayFieldingGrade == 3) {
                    fieldingThreeManOnFirstAndSecond(playerNumber);
                }
            }
        }

        else if(runnerOnFirstAndThird()) {
            if(playerNumber <= 11) {
                if(awayPitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnFirstAndThird(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnFirstAndThird(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnFirstAndThird(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnFirstAndThird(playerNumber);
                }
            } else {
                if(awayFieldingGrade == 1) {
                    fieldingOneManOnFirstAndThird(playerNumber);
                }
                if(awayFieldingGrade == 2) {
                    fieldingTwoManOnFirstAndThird(playerNumber);
                }
                if(awayFieldingGrade == 3) {
                    fieldingThreeManOnFirstAndThird(playerNumber);
                }
            }
        }

        else if(runnerOnSecondAndThird()) {
            if(playerNumber <= 11) {
                if(awayPitcherGrade.equalsIgnoreCase("A")) {
                    gradeAManOnSecondAndThird(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("B")) {
                    gradeBManOnSecondAndThird(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("C")) {
                    gradeCManOnSecondAndThird(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("D")) {
                    gradeDManOnSecondAndThird(playerNumber);
                }
            } else {
                if(awayFieldingGrade == 1) {
                    fieldingOneManOnSecondAndThird(playerNumber);
                }
                if(awayFieldingGrade == 2) {
                    fieldingTwoManOnSecondAndThird(playerNumber);
                }
                if(awayFieldingGrade == 3) {
                    fieldingThreeManOnSecondAndThird(playerNumber);
                }
            }
        }

        else if(basesLoaded()) {
            if(playerNumber <= 11) {
                if(awayPitcherGrade.equalsIgnoreCase("A")) {
                    gradeABasesLoaded(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("B")) {
                    gradeBBasesLoaded(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("C")) {
                    gradeCBasesLoaded(playerNumber);
                }
                if(awayPitcherGrade.equalsIgnoreCase("D")) {
                    gradeDBasesLoaded(playerNumber);
                }
            } else {
                if(awayFieldingGrade == 1) {
                    fieldingOneBasesLoaded(playerNumber);
                }
                if(awayFieldingGrade == 2) {
                    fieldingTwoBasesLoaded(playerNumber);
                }
                if(awayFieldingGrade == 3) {
                    fieldingThreeBasesLoaded(playerNumber);
                }
            }
        }
        checkIfHomeScoresInNinth();
        checkIfHomeScoresInExtraInnings();
    }

    public void quitGame() {
        gameStage.close();
        if(!endedNaturally) {
            writeLogFile();
        }
    }

    private int[] roll() {

        int outcomeRed = (int)(Math.floor(Math.random() * 6) + 1);
        int outcomeWhite = (int)(Math.floor(Math.random() * 6) + 1);
        int[] outcomes;

        Timeline timelineRed = new Timeline(new KeyFrame(Duration.seconds(0.02), evt -> redDie1.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> redDie1.setVisible(true)),
                new KeyFrame(Duration.seconds(0.02), evt -> redDie2.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> redDie2.setVisible(true)),
                new KeyFrame(Duration.seconds(0.02), evt -> redDie3.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> redDie3.setVisible(true)),
                new KeyFrame(Duration.seconds(0.02), evt -> redDie4.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> redDie4.setVisible(true)),
                new KeyFrame(Duration.seconds(0.02), evt -> redDie5.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> redDie5.setVisible(true)),
                new KeyFrame(Duration.seconds(0.02), evt -> redDie6.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> redDie6.setVisible(true)));
        timelineRed.setCycleCount(60);

        Timeline timelineWhite = new Timeline(new KeyFrame(Duration.seconds(0.02), evt -> whiteDie1.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> whiteDie1.setVisible(true)),
                new KeyFrame(Duration.seconds(0.02), evt -> whiteDie2.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> whiteDie2.setVisible(true)),
                new KeyFrame(Duration.seconds(0.02), evt -> whiteDie3.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> whiteDie3.setVisible(true)),
                new KeyFrame(Duration.seconds(0.02), evt -> whiteDie4.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> whiteDie4.setVisible(true)),
                new KeyFrame(Duration.seconds(0.02), evt -> whiteDie5.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> whiteDie5.setVisible(true)),
                new KeyFrame(Duration.seconds(0.02), evt -> whiteDie6.setVisible(false)),
                new KeyFrame(Duration.seconds(0.01), evt -> whiteDie6.setVisible(true)));
        timelineWhite.setCycleCount(60);

        timelineRed.play();
        timelineWhite.play();

        timelineRed.setOnFinished(event -> {
            switch(outcomeRed) {
                case 1:
                    redDie1.setVisible(true);
                    break;
                case 2:
                    redDie2.setVisible(true);
                    break;
                case 3:
                    redDie3.setVisible(true);
                    break;
                case 4:
                    redDie4.setVisible(true);
                    break;
                case 5:
                    redDie5.setVisible(true);
                    break;
                case 6:
                    redDie6.setVisible(true);
                    break;
            }
        });

        timelineWhite.setOnFinished(event -> {
            switch(outcomeWhite) {
                case 1:
                    whiteDie1.setVisible(true);
                    break;
                case 2:
                    whiteDie2.setVisible(true);
                    break;
                case 3:
                    whiteDie3.setVisible(true);
                    break;
                case 4:
                    whiteDie4.setVisible(true);
                    break;
                case 5:
                    whiteDie5.setVisible(true);
                    break;
                case 6:
                    whiteDie6.setVisible(true);
                    break;
            }

            roll.setDisable(false);

            if (teamAtBat == 0 && rollAgain == 0) {
                printAwayNum();
            } else if(teamAtBat == 0 && rollAgain == 1){
                rollAgainAway();
            } else if(teamAtBat == 1 && rollAgain == 0) {
                printHomeNum();
            } else {
                rollAgainHome();
            }
        });

        outcomes = new int[] {outcomeRed, outcomeWhite};
        return outcomes;
    }

    private boolean basesEmpty() {
        return !firstBaseDot.isVisible() && !secondBaseDot.isVisible() && !thirdBaseDot.isVisible();
    }

    private boolean runnerOnFirst() {
        return firstBaseDot.isVisible() && !secondBaseDot.isVisible() && !thirdBaseDot.isVisible();
    }

    private boolean runnerOnSecond() {
        return secondBaseDot.isVisible() && !firstBaseDot.isVisible() && !thirdBaseDot.isVisible();
    }

    private boolean runnerOnThird() {
        return thirdBaseDot.isVisible() && !secondBaseDot.isVisible() && !firstBaseDot.isVisible();
    }

    private boolean runnerOnFirstAndSecond() {
        return firstBaseDot.isVisible() && secondBaseDot.isVisible() && !thirdBaseDot.isVisible();
    }

    private boolean runnerOnFirstAndThird() {
        return firstBaseDot.isVisible() && thirdBaseDot.isVisible() && !secondBaseDot.isVisible();
    }

    private boolean runnerOnSecondAndThird() {
        return secondBaseDot.isVisible() && thirdBaseDot.isVisible() && !firstBaseDot.isVisible();
    }

    private boolean basesLoaded() {
        return  firstBaseDot.isVisible() && secondBaseDot.isVisible() && thirdBaseDot.isVisible();
    }

    private void gradeABasesEmpty(int playerNumber) {
        switch (playerNumber) {
            case 1:
                outcomeDescription = "HOMERUN over right field fence";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                   awayScore++;
                   awayScoreLabel.setText(Integer.toString(awayScore));
                   awayNextBatter();
                } else {
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 2:
                outcomeDescription = "TRIPLE to left center";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 3:
                outcomeDescription = "TRIPLE to right";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 4:
                outcomeDescription = "DOUBLE over 3rd";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 5:
                outcomeDescription = "DOUBLE over 1st";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 6:
                outcomeDescription = "DOUBLE to right center";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 7:
                outcomeDescription = "SINGLE to right";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 8:
                outcomeDescription = "Pop fly out PO-3B";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                outcomeDescription = "Strikeout; PO-C";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                outcomeDescription = "SINGLE to center";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 11:
                outcomeDescription = "SINGLE to left; batter steals 2nd on first pitch to next batter; one strike" +
                        " on batter";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
        }
    }

    private void gradeBBasesEmpty(int playerNumber) {
        switch (playerNumber) {
            case 1:
                outcomeDescription = "HOMERUN over right field fence";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    awayScore++;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 2:
                outcomeDescription = "TRIPLE to left center";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 3:
                outcomeDescription ="TRIPLE to right";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 4:
                outcomeDescription = "DOUBLE over 3rd";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 5:
                outcomeDescription ="DOUBLE over 1st";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 6:
                outcomeDescription = "DOUBLE to right center";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 7:
                outcomeDescription = "SINGLE to right";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 8:
                outcomeDescription = "Fly out PO-CF";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                outcomeDescription = "SINGLE over short";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 10:
                outcomeDescription = "SINGLE to center";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 11:
                outcomeDescription = "SINGLE to left; batter steals 2nd on first pitch to next batter; one strike" +
                        " on batter";
                situationOutcome.setText(outcomeDescription);
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
        }
    }

    private void gradeCBasesEmpty(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over right field fence");
                if(teamAtBat == 0) {
                    awayScore++;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to left center");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("TRIPLE to right");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("DOUBLE over 3rd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("DOUBLE over 1st");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to right center");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to right");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE to left center");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 9:
                situationOutcome.setText("Pop fly out PO-3B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE to center");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; batter steals 2nd on first pitch to next batter; one strike" +
                        " on batter");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeDBasesEmpty(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over right field fence");
                if(teamAtBat == 0) {
                    awayScore++;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to left center");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("TRIPLE to right");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("DOUBLE over 3rd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("DOUBLE over 1st");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to right center");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to right");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE to left center");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE over short");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE to center");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; batter steals 2nd on first pitch to next batter; one strike" +
                        " on batter");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingOneBasesEmpty(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Out at 1st; A-3B PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; batter takes 1st (ZZ Pitcher : 2 balls, 0 strikes)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("ZZ")) {
                        ball += 2;
                        strike = 0;
                        ballsNum.setText(Integer.toString(ball));
                        strikesNum.setText(Integer.toString(strike));
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("ZZ")) {
                        ball += 2;
                        strike = 0;
                        ballsNum.setText(Integer.toString(ball));
                        strikesNum.setText(Integer.toString(strike));
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 15:
                situationOutcome.setText("Fly out; PO-LF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 16:
                situationOutcome.setText("Fly out; PO-CF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("Fly out; PO-RF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; E-SS");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; E-3B (If 2 outs, Out at 1st; A-3B PO-1B)");
                if(teamAtBat == 0) {
                    if(out == 2) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    if(out == 2) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; E-2B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("Fly out; PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 22:
                situationOutcome.setText("Out at 1st; A-1B PO-P");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 23:
                situationOutcome.setText("1st on error; E-LF");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 24:
                situationOutcome.setText("Out at 1st; A-SS PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Out at 1st; A-2B PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Out at 1st; A-2B PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; A-3B PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; A-SS PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; A-P PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; PO-LF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; PO-CF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; PO-RF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Pop fly out; PO-2B (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Pop fly out; PO-SS (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-C");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Ball");
                if(teamAtBat == 0) {
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                    if(ball == 4) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                    if(ball == 4) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 37:
                situationOutcome.setText("Strike (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                awayNextBatter();
                            }
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                homeNextBatter();
                            }
                        }
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Ball (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            firstBaseDot.setVisible(true);
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            firstBaseDot.setVisible(true);
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Strike (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                awayNextBatter();
                            }
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                homeNextBatter();
                            }
                        }
                    }
                }
                break;
            case 40:
                situationOutcome.setText("Foul (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        if(strike == 0 || strike == 1) {
                            strike++;
                            strikesNum.setText(Integer.toString(strike));
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        if(strike == 0 || strike == 1) {
                            strike++;
                            strikesNum.setText(Integer.toString(strike));
                        }
                    }
                }
                break;
            case 41:
                situationOutcome.setText("1st on error; E-3B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingTwoBasesEmpty(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Out at 1st; A-3B PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; batter takes 1st (ZZ Pitcher : 2 balls, 0 strikes)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("ZZ")) {
                        ball += 2;
                        strike = 0;
                        ballsNum.setText(Integer.toString(ball));
                        strikesNum.setText(Integer.toString(strike));
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("ZZ")) {
                        ball += 2;
                        strike = 0;
                        ballsNum.setText(Integer.toString(ball));
                        strikesNum.setText(Integer.toString(strike));
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 15:
                situationOutcome.setText("Fly out; PO-LF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 16:
                situationOutcome.setText("Fly out; PO-CF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("Fly out; PO-RF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; E-SS");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; E-3B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; E-2B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("1st on error; E-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Out at 1st; A-1B PO-P");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 23:
                situationOutcome.setText("1st on error; E-CF");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 24:
                situationOutcome.setText("Out at 1st; A-SS PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Out at 1st; A-2B PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Out at 1st; A-2B PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; A-3B PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; A-SS PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; A-P PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; PO-LF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; PO-CF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; PO-RF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Pop fly out; PO-2B (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Pop fly out; PO-SS (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-C");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Ball");
                if(teamAtBat == 0) {
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                    if(ball == 4) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                    if(ball == 4) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 37:
                situationOutcome.setText("Strike (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                awayNextBatter();
                            }
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                homeNextBatter();
                            }
                        }
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Ball (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            firstBaseDot.setVisible(true);
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            firstBaseDot.setVisible(true);
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Strike (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                awayNextBatter();
                            }
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                homeNextBatter();
                            }
                        }
                    }
                }
                break;
            case 40:
                situationOutcome.setText("Foul (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        if(strike == 0 || strike == 1) {
                            strike++;
                            strikesNum.setText(Integer.toString(strike));
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        if(strike == 0 || strike == 1) {
                            strike++;
                            strikesNum.setText(Integer.toString(strike));
                        }
                    }
                }
                break;
            case 41:
                situationOutcome.setText("1st and 2nd on error; E-P");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingThreeBasesEmpty(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Out at 1st; A-3B PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; batter takes 1st (ZZ Pitcher : 2 balls, 0 strikes)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("ZZ")) {
                        ball += 2;
                        strike = 0;
                        ballsNum.setText(Integer.toString(ball));
                        strikesNum.setText(Integer.toString(strike));
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("ZZ")) {
                        ball += 2;
                        strike = 0;
                        ballsNum.setText(Integer.toString(ball));
                        strikesNum.setText(Integer.toString(strike));
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 15:
                situationOutcome.setText("1st and 2nd on error; E-LF");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("SINGLE; batter to 2nd on error; E-CF");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE; batter to 2nd on error; E-RF");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; E-SS");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; E-3B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; E-2B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("1st on error; E-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("1st on error; E-P");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 23:
                situationOutcome.setText("1st on error; E-SS");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 24:
                situationOutcome.setText("Out at 1st; A-SS PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Out at 1st; A-2B PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Out at 1st; A-2B PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; A-3B PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; A-SS PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; A-P PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; PO-LF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; PO-CF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; PO-RF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Pop fly out; PO-2B (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Pop fly out; PO-SS (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-C");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Ball");
                if(teamAtBat == 0) {
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                    if(ball == 4) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                    if(ball == 4) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 37:
                situationOutcome.setText("Strike (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                awayNextBatter();
                            }
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                homeNextBatter();
                            }
                        }
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Ball (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            firstBaseDot.setVisible(true);
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            firstBaseDot.setVisible(true);
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Strike (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                awayNextBatter();
                            }
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        strike++;
                        strikesNum.setText(Integer.toString(strike));
                        if(strike == 3) {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            if(out == 3) {
                                threeOuts();
                            } else {
                                homeNextBatter();
                            }
                        }
                    }
                }
                break;
            case 40:
                situationOutcome.setText("Foul (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        if(strike == 0 || strike == 1) {
                            strike++;
                            strikesNum.setText(Integer.toString(strike));
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        if(strike == 0 || strike == 1) {
                            strike++;
                            strikesNum.setText(Integer.toString(strike));
                        }
                    }
                }
                break;
            case 41:
                situationOutcome.setText("1st and 2nd on error; E-SS");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(true);
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeAManOnFirst(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                if(teamAtBat == 0) {
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    firstBaseDot.setVisible(false);
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    firstBaseDot.setVisible(false);
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE along right field foul line");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to center; runner scores (S out at home; A-CF PO-C; If 2 outs, S " +
                        "scores)");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        if (out == 2) {
                            awayScore++;
                            awayScoreLabel.setText(Integer.toString(awayScore));
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        } else {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    } else {
                        awayScore++;
                        awayScoreLabel.setText(Integer.toString(awayScore));
                        firstBaseDot.setVisible(false);
                        awayNextBatter();
                    }
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        if (out == 2) {
                            homeScore++;
                            homeScoreLabel.setText(Integer.toString(homeScore));
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        } else {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    } else {
                        homeScore++;
                        homeScoreLabel.setText(Integer.toString(homeScore));
                        firstBaseDot.setVisible(false);
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to left");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    firstBaseDot.setVisible(false);
                    awayScore++;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    firstBaseDot.setVisible(false);
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over right center field fence");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(false);
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE over 1st; runner to 3rd (F scores); If 2 outs, any runner scores");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        awayScore++;
                        awayScoreLabel.setText(Integer.toString(awayScore));
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        homeScore++;
                        homeScoreLabel.setText(Integer.toString(homeScore));
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("Out at 1st; runner to 2nd; A-3B PO-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if (out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if (out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 8:
                situationOutcome.setText("Out at 1st; runner to 2nd; A-1B PO-P");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if (out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if (out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE; beats out infield hit; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE to right center; runner to 3rd; batter steals 2nd on second pitch; " +
                        "two strikes on batter");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    awayNextBatter();
                    strike += 2;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    homeNextBatter();
                    strike += 2;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE over 1st; runner to 3rd; batter steals 2nd on third pitch; " +
                        "two balls, one strike on batter");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    awayNextBatter();
                    strike ++;
                    strikesNum.setText(Integer.toString(strike));
                    ball += 2;
                    ballsNum.setText(Integer.toString(ball));
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    homeNextBatter();
                    strike ++;
                    strikesNum.setText(Integer.toString(strike));
                    ball += 2;
                    ballsNum.setText(Integer.toString(ball));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeBManOnFirst(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                if(teamAtBat == 0) {
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    firstBaseDot.setVisible(false);
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    firstBaseDot.setVisible(false);
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE along right field foul line");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to center; runner scores (S out at home; A-CF PO-C; If 2 outs, S " +
                        "scores)");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        if (out == 2) {
                            awayScore++;
                            awayScoreLabel.setText(Integer.toString(awayScore));
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        } else {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    } else {
                        awayScore++;
                        awayScoreLabel.setText(Integer.toString(awayScore));
                        firstBaseDot.setVisible(false);
                        awayNextBatter();
                    }
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        if (out == 2) {
                            homeScore++;
                            homeScoreLabel.setText(Integer.toString(homeScore));
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        } else {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    } else {
                        homeScore++;
                        homeScoreLabel.setText(Integer.toString(homeScore));
                        firstBaseDot.setVisible(false);
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to left");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    firstBaseDot.setVisible(false);
                    awayScore++;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    firstBaseDot.setVisible(false);
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over right center field fence");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(false);
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE over 1st; runner to 3rd (F scores); If 2 outs, any runner scores");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        awayScore++;
                        awayScoreLabel.setText(Integer.toString(awayScore));
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        homeScore++;
                        homeScoreLabel.setText(Integer.toString(homeScore));
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to right; runner to third; F batter to 2nd on throw to 3rd");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(away.get(playerAtBatAway).getSpeed().equalsIgnoreCase("F")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        firstBaseDot.setVisible(false);
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    }
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(home.get(playerAtBatHome).getSpeed().equalsIgnoreCase("F")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        firstBaseDot.setVisible(false);
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    }
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("Out at 1st; runner to 2nd; A-3B PO-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if (out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if (out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE; beats out infield hit; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE to right center; runner to 3rd; batter steals 2nd on second pitch; " +
                        "two strikes on batter");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    awayNextBatter();
                    strike += 2;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    homeNextBatter();
                    strike += 2;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE over 1st; runner to 3rd; batter steals 2nd on third pitch; " +
                        "two balls, one strike on batter");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    awayNextBatter();
                    strike ++;
                    strikesNum.setText(Integer.toString(strike));
                    ball += 2;
                    ballsNum.setText(Integer.toString(ball));
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    homeNextBatter();
                    strike ++;
                    strikesNum.setText(Integer.toString(strike));
                    ball += 2;
                    ballsNum.setText(Integer.toString(ball));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeCManOnFirst(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                if(teamAtBat == 0) {
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    firstBaseDot.setVisible(false);
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    firstBaseDot.setVisible(false);
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE along right field foul line");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to center; runner scores (S out at home; A-CF PO-C; If 2 outs, S " +
                        "scores)");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        if (out == 2) {
                            awayScore++;
                            awayScoreLabel.setText(Integer.toString(awayScore));
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        } else {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    } else {
                        awayScore++;
                        awayScoreLabel.setText(Integer.toString(awayScore));
                        firstBaseDot.setVisible(false);
                        awayNextBatter();
                    }
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        if (out == 2) {
                            homeScore++;
                            homeScoreLabel.setText(Integer.toString(homeScore));
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        } else {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    } else {
                        homeScore++;
                        homeScoreLabel.setText(Integer.toString(homeScore));
                        firstBaseDot.setVisible(false);
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to left");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    firstBaseDot.setVisible(false);
                    awayScore++;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    firstBaseDot.setVisible(false);
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over right center field fence");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(false);
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE over 1st; runner to 3rd (F scores); If 2 outs, any runner scores");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        awayScore++;
                        awayScoreLabel.setText(Integer.toString(awayScore));
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        homeScore++;
                        homeScoreLabel.setText(Integer.toString(homeScore));
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to right; runner to third; F batter to 2nd on throw to 3rd");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(away.get(playerAtBatAway).getSpeed().equalsIgnoreCase("F")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        firstBaseDot.setVisible(false);
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    }
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(home.get(playerAtBatHome).getSpeed().equalsIgnoreCase("F")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        firstBaseDot.setVisible(false);
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    }
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE; line drive to center; runner to 3rd (S out at 3rd; A-CF PO-3B)");
                if(teamAtBat == 0) {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if (out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if (out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("Out at 1st; runner to 2nd; A-3B PO-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if (out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if (out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE to right center; runner to 3rd; batter steals 2nd on second pitch; " +
                        "two strikes on batter");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    awayNextBatter();
                    strike += 2;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    homeNextBatter();
                    strike += 2;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE over 1st; runner to 3rd; batter steals 2nd on third pitch; " +
                        "two balls, one strike on batter");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    awayNextBatter();
                    strike ++;
                    strikesNum.setText(Integer.toString(strike));
                    ball += 2;
                    ballsNum.setText(Integer.toString(ball));
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    homeNextBatter();
                    strike ++;
                    strikesNum.setText(Integer.toString(strike));
                    ball += 2;
                    ballsNum.setText(Integer.toString(ball));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeDManOnFirst(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                if(teamAtBat == 0) {
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    firstBaseDot.setVisible(false);
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    firstBaseDot.setVisible(false);
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE along right field foul line");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to center; runner scores (S out at home; A-CF PO-C; If 2 outs, S " +
                        "scores)");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        if (out == 2) {
                            awayScore++;
                            awayScoreLabel.setText(Integer.toString(awayScore));
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        } else {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    } else {
                        awayScore++;
                        awayScoreLabel.setText(Integer.toString(awayScore));
                        firstBaseDot.setVisible(false);
                        awayNextBatter();
                    }
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        if (out == 2) {
                            homeScore++;
                            homeScoreLabel.setText(Integer.toString(homeScore));
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        } else {
                            out++;
                            outsNum.setText(Integer.toString(out));
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    } else {
                        homeScore++;
                        homeScoreLabel.setText(Integer.toString(homeScore));
                        firstBaseDot.setVisible(false);
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to left");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    firstBaseDot.setVisible(false);
                    awayScore++;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    firstBaseDot.setVisible(false);
                    homeScore++;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over right center field fence");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(false);
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE over 1st; runner to 3rd (F scores); If 2 outs, any runner scores");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        awayScore++;
                        awayScoreLabel.setText(Integer.toString(awayScore));
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        homeScore++;
                        homeScoreLabel.setText(Integer.toString(homeScore));
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to right; runner to third; F batter to 2nd on throw to 3rd");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(away.get(playerAtBatAway).getSpeed().equalsIgnoreCase("F")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        firstBaseDot.setVisible(false);
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    }
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(home.get(playerAtBatHome).getSpeed().equalsIgnoreCase("F")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        firstBaseDot.setVisible(false);
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    }
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE; line drive to center; runner to 3rd (S out at 3rd; A-CF PO-3B)");
                if(teamAtBat == 0) {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if (out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if (out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE; beats out infield hit; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE to right center; runner to 3rd; batter steals 2nd on second pitch; " +
                        "two strikes on batter");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    awayNextBatter();
                    strike += 2;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    homeNextBatter();
                    strike += 2;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE over 1st; runner to 3rd; batter steals 2nd on third pitch; " +
                        "two balls, one strike on batter");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    awayNextBatter();
                    strike ++;
                    strikesNum.setText(Integer.toString(strike));
                    ball += 2;
                    ballsNum.setText(Integer.toString(ball));
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(true);
                    homeNextBatter();
                    strike ++;
                    strikesNum.setText(Integer.toString(strike));
                    ball += 2;
                    ballsNum.setText(Integer.toString(ball));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingOneManOnFirst(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; A-2B PO-SS A-SS PO-1B (W-Base on balls)");
                if(teamAtBat == 0) {
                   if(homePitcherRating.equalsIgnoreCase("W")) {
                       secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                       firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                       secondBaseDot.setVisible(true);
                       awayNextBatter();
                   } else {
                       firstBaseDot.setVisible(false);
                       out += 2;
                       outsNum.setText(Integer.toString(out));
                       if(out >= 3) {
                           threeOuts();
                       } else {
                           awayNextBatter();
                       }
                   }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        secondBaseDot.setVisible(true);
                        homeNextBatter();
                    } else {
                        firstBaseDot.setVisible(false);
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls, runner to 2nd; batter to 1st (ZZ Pitcher : ball)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("ZZ")) {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("ZZ")) {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 15:
                situationOutcome.setText("Hit by pitcher; batter to 1st; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("Fly out; runner holds (F to 2nd); PO-CF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if (firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            secondBaseDot.setVisible(true);
                            firstBaseDot.setVisible(false);
                        }
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if (firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            secondBaseDot.setVisible(true);
                            firstBaseDot.setVisible(false);
                        }
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; runner out at 3rd; A-RF PO-3B; batter to 2nd on throw to" +
                        "3rd");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        secondBaseDot.setVisible(true);
                        firstBaseDot.setVisible(false);
                        awayNextBatter();
                    }
                } else {
                    out++;
                    if(out == 3) {
                        threeOuts();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        secondBaseDot.setVisible(true);
                        firstBaseDot.setVisible(false);
                        homeNextBatter();
                    }
                }
                break;
            case 18:
                situationOutcome.setText("Out at 1st; runner to 2nd; A-SS PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        homeNextBatter();
                    }
                }
                break;
            case 19:
                situationOutcome.setText("Out at 1st; runner to 2nd; A-3B PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        homeNextBatter();
                    }
                }
                break;
            case 20:
                situationOutcome.setText("Out at 1st; runner to 2nd; A-2B PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        homeNextBatter();
                    }
                }
                break;
            case 21:
                situationOutcome.setText("1st and 2nd on error; runner to 3rd; A-SS E-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    thirdBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    thirdBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Hit by pitcher; batter to 1st; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 23:
                situationOutcome.setText("Runner out stealing; A-C PO-SS; If 2 outs then balk runner to 2nd");
                if(teamAtBat == 0) {
                    if(out == 2) {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        firstBaseDot.setVisible(false);
                    }
                } else {
                    if(out == 2) {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        firstBaseDot.setVisible(false);
                    }
                }
                break;
            case 24:
                situationOutcome.setText("Double play; A-SS PO-2B A-2B PO-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; A-2B PO-SS A-SS PO-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Batter safe at first; FC; runner out at 2nd; A-2B PO-SS");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Double play; A-3B PO-2B A-2B PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        firstBaseDot.setVisible(false);
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if (out >= 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        firstBaseDot.setVisible(false);
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if (out >= 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Safe at first; FC; runner out; A-SS PO-2B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runner to 2nd; A-P PO-1B (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(homePitcherRating.equalsIgnoreCase("Y")) {
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseDot.setVisible(false);
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            awayNextBatter();
                        }
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(awayPitcherRating.equalsIgnoreCase("Y")) {
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseDot.setVisible(false);
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runner holds; PO-LF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runner holds; PO-CF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runner holds; PO-RF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Pop fly out; PO-3B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("High fly out; PO-SS (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-C (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if (out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if (out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch, runner to 2nd");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                } else {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                }
                break;
            case 37:
                situationOutcome.setText("Runner caught off 1st; A-P PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatAway--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatHome--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Runner steals 2nd (S holds 1st)");
                if(teamAtBat == 0) {
                    if(!firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        secondBaseDot.setVisible(true);
                        firstBaseDot.setVisible(false);
                    }
                } else {
                    if(!firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        secondBaseDot.setVisible(true);
                        firstBaseDot.setVisible(false);
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Runner out stealing; A-C PO-2B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatAway--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatHome--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                }
                break;
            case 40:
                situationOutcome.setText("Runner out stealing; A-C PO-SS");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatAway--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatHome--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                }
                break;
            case 41:
                situationOutcome.setText("Double play; A-2B PO-SS A-SS PO-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter to 1st; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingTwoManOnFirst(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; A-3B PO-2B A-2B PO-1B (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        secondBaseDot.setVisible(true);
                        awayNextBatter();
                    } else {
                        firstBaseDot.setVisible(false);
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        secondBaseDot.setVisible(true);
                        homeNextBatter();
                    } else {
                        firstBaseDot.setVisible(false);
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls, runner to 2nd; batter to 1st (ZZ Pitcher : ball)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("ZZ")) {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("ZZ")) {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 15:
                situationOutcome.setText("Hit by pitcher; batter to 1st; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("SINGLE; runner to 2nd (F to 3rd; E-CF); If 2 outs, and runner to 3rd");
                if(teamAtBat == 0) {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    }
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    }
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; runner to 3rd (F scores); batter to 2nd on throw");
                if(teamAtBat == 0) {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        awayScore++;
                        awayScoreLabel.setText(Integer.toString(awayScore));
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    }
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        homeScore++;
                        homeScoreLabel.setText(Integer.toString(homeScore));
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    }
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runner to 3rd; E-SS");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("SINGLE to left; runner to 2nd; If 2 outs, then out at 1st; A-3B PO-1B");
                if(teamAtBat == 0) {
                    if(out == 2) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        threeOuts();
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    if(out == 2) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        threeOuts();
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; runner to 3rd; E-2B");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("1st and 2nd on error; runner to 3rd; A-SS E-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    thirdBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    thirdBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Hit by pitcher; batter to 1st; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 23:
                situationOutcome.setText("Runner out stealing; A-C PO-2B; If 2 outs then balk runner to 2nd");
                if(teamAtBat == 0) {
                    if(out == 2) {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                } else {
                    if(out == 2) {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                }
                break;
            case 24:
                situationOutcome.setText("Double play; A-SS PO-2B A-2B PO-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; A-2B PO-SS A-SS PO-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Batter safe at first; FC; runner out at 2nd; A-2B PO-SS");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Double play; A-3B PO-2B A-2B PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        firstBaseDot.setVisible(false);
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if (out >= 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        firstBaseDot.setVisible(false);
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if (out >= 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; FC; runner to 2nd; A-SS PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Safe at 1st; runner out; FC; A-P PO-SS; If 2 outs, A-P PO-1B (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(homePitcherRating.equalsIgnoreCase("Y")) {
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(awayPitcherRating.equalsIgnoreCase("Y")) {
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runner holds; PO-LF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runner holds; PO-CF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runner holds; PO-RF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("High fly out; PO-3B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Pop fly out; PO-SS (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-C (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if (out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if (out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch, runner to 2nd");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                } else {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                }
                break;
            case 37:
                situationOutcome.setText("Runner caught off 1st; A-P PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatAway--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatHome--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Runner steals 2nd (S holds 1st)");
                if(teamAtBat == 0) {
                    if(!firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        secondBaseDot.setVisible(true);
                        firstBaseDot.setVisible(false);
                    }
                } else {
                    if(!firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        secondBaseDot.setVisible(true);
                        firstBaseDot.setVisible(false);
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Runner out stealing; A-C PO-2B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatAway--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatHome--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                }
                break;
            case 40:
                situationOutcome.setText("Safe at 1st; runner out; FC; A-1B PO-SS");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 41:
                situationOutcome.setText("Batter safe at 1st; runner out at 2nd; FC; A-SS PO-2B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter to 1st; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingThreeManOnFirst(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Safe at 1st; runner out; FC; A-2B PO-SS (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        secondBaseDot.setVisible(true);
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        secondBaseDot.setVisible(true);
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls, runner to 2nd; batter to 1st (ZZ Pitcher : ball)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("ZZ")) {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("ZZ")) {
                        ball++;
                        ballsNum.setText(Integer.toString(ball));
                        if(ball == 4) {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 15:
                situationOutcome.setText("Hit by pitcher; batter to 1st; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("1st on error; runner to 2nd; E-CF; (F to 3rd); If 2 outs, and runner to 3rd");
                if(teamAtBat == 0) {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    }
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    } else {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    }
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; runner to 2nd; runner to 3rd and batter to 2nd on fumble; " +
                        "E-RF");
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    thirdBaseDot.setVisible(true);
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    thirdBaseDot.setVisible(true);
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runner to 3rd; E-SS");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("SINGLE to left; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; runner to 3rd; E-2B");
                if(teamAtBat == 0) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("1st and 2nd on error; runner to 3rd; A-SS E-1B (F scores)");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        awayScore++;
                        awayScoreLabel.setText(Integer.toString(awayScore));
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    }
                    awayNextBatter();
                } else {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        homeScore++;
                        homeScoreLabel.setText(Integer.toString(homeScore));
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    }
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Hit by pitcher; batter to 1st; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 23:
                situationOutcome.setText("Runner out stealing; A-C PO-2B; If 2 outs then balk runner to 2nd");
                if(teamAtBat == 0) {
                    if(out == 2) {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                } else {
                    if(out == 2) {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                }
                break;
            case 24:
                situationOutcome.setText("Double play; A-SS PO-2B A-2B PO-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; A-2B PO-SS A-SS PO-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Batter safe at first; FC; runner out at 2nd; A-2B PO-SS");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runner to 2nd; A-3B PO-1B (X-SO; PO-C)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; FC; runner to 2nd; A-SS PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runner to 2nd; A-P PO-1B (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("Y")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("Y")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runner holds; PO-LF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runner holds; PO-CF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runner holds; PO-RF");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Pop fly out; PO-3B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("High fly out; PO-SS (Y-SO; PO-C)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-C (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if (out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        secondBaseDot.setVisible(true);
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if (out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch, runner to 2nd");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                } else {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                }
                break;
            case 37:
                situationOutcome.setText("Runner caught off 1st; A-P PO-1B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatAway--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatHome--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Runner steals 2nd (S holds 1st)");
                if(teamAtBat == 0) {
                    if(!firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        secondBaseDot.setVisible(true);
                        firstBaseDot.setVisible(false);
                    }
                } else {
                    if(!firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        secondBaseDot.setVisible(true);
                        firstBaseDot.setVisible(false);
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Runner out stealing; A-C PO-2B");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatAway--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        playerAtBatHome--;
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                    }
                }
                break;
            case 40:
                situationOutcome.setText("Out at 1st; runner to 2nd; A-1B PO-P (F to 3rd)");
                if(teamAtBat == 0) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            thirdBaseDot.setVisible(true);
                            thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        } else {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        }
                        awayNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        firstBaseDot.setVisible(false);
                        if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            thirdBaseDot.setVisible(true);
                            thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        } else {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        }
                        homeNextBatter();
                    }
                }
                break;
            case 41:
                situationOutcome.setText("Double play; A-3B PO-2B A-2B PO-1B");
                if(teamAtBat == 0) {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        awayNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter to 1st; runner to 2nd");
                if(teamAtBat == 0) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeAManOnSecond(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over center field fence");
                if (teamAtBat == 0) {
                    secondBaseDot.setVisible(false);
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(false);
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to deep center");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                if (teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("HOMERUN to deep right field");
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to right center");
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over left center field fence");
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to left center; runner scores");
                if (teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to right; runner scores");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("Out at 1st; runner to 3rd A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("Out at 1st; runner to 3rd; A-SS PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("Out at 1st; runner to 3rd; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; runner out at home(F scores); A-LF PO-C; If 2 outs, any " +
                        "runner scores; batter steals 2nd on first pitch; 1 strike on next batter");
                if (teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        homeScore++;
                        updateHomeScore();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeBManOnSecond(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over center field fence");
                if (teamAtBat == 0) {
                    secondBaseDot.setVisible(false);
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(false);
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to deep center");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                if (teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("HOMERUN to deep right field");
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to right center");
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over left center field fence");
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to left center; runner scores");
                if (teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to right; runner scores");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("Out at 1st; runner to 3rd A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE over 2nd; runner scores (S out at home; A-CF PO-C; batter to 2nd)");
                if(teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        firstBaseDot.setVisible(true);
                        secondBaseDot.setVisible(false);
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        firstBaseDot.setVisible(true);
                        secondBaseDot.setVisible(false);
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("Out at 1st; runner to 3rd; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; runner out at home(F scores); A-LF PO-C; If 2 outs, any " +
                        "runner scores; batter steals 2nd on first pitch; 1 strike on next batter");
                if (teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        homeScore++;
                        updateHomeScore();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeCManOnSecond(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over center field fence");
                if (teamAtBat == 0) {
                    secondBaseDot.setVisible(false);
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(false);
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to deep center");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                if (teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("HOMERUN to deep right field");
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to right center");
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over left center field fence");
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to left center; runner scores");
                if (teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to right; runner scores");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE over 2nd; runner scores (S out at home; A-CF PO-C; batter to 2nd)");
                if(teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        firstBaseDot.setVisible(true);
                        secondBaseDot.setVisible(false);
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        firstBaseDot.setVisible(true);
                        secondBaseDot.setVisible(false);
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("Out at 1st; runner to 3rd; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE - infield hit; runner to 3rd; (F scores)");
                firstBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        homeScore++;
                        updateHomeScore();
                    } else {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; runner out at home(F scores); A-LF PO-C; If 2 outs, any " +
                        "runner scores; batter steals 2nd on first pitch; 1 strike on next batter");
                if (teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        homeScore++;
                        updateHomeScore();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeDManOnSecond(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over center field fence");
                if (teamAtBat == 0) {
                    secondBaseDot.setVisible(false);
                    awayScore += 2;
                    awayScoreLabel.setText(Integer.toString(awayScore));
                    awayNextBatter();
                } else {
                    secondBaseDot.setVisible(false);
                    homeScore += 2;
                    homeScoreLabel.setText(Integer.toString(homeScore));
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to deep center");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                if (teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("HOMERUN to deep right field");
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to right center");
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over left center field fence");
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to left center; runner scores");
                if (teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to right; runner scores");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE over 2nd; runner scores (S out at home; A-CF PO-C; batter to 2nd)");
                if(teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        firstBaseDot.setVisible(true);
                        secondBaseDot.setVisible(false);
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        firstBaseDot.setVisible(true);
                        secondBaseDot.setVisible(false);
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE thru short; runner scores (S out at home; A-LF PO-C; batter to 2nd)");
                if(teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        firstBaseDot.setVisible(true);
                        secondBaseDot.setVisible(false);
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        firstBaseDot.setVisible(true);
                        secondBaseDot.setVisible(false);
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE - infield hit; runner to 3rd; (F scores)");
                firstBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        homeScore++;
                        updateHomeScore();
                    } else {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                    }
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; runner out at home(F scores); A-LF PO-C; If 2 outs, any " +
                        "runner scores; batter steals 2nd on first pitch; 1 strike on next batter");
                if (teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        homeScore++;
                        updateHomeScore();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                    }
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    strike++;
                    strikesNum.setText(Integer.toString(strike));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingOneManOnSecond(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("Foul out; runner holds; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 16:
                situationOutcome.setText("SINGLE to center; runner out at home; batter to 2nd on throw; A-CF PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("Fly out; runner holds (F to 3rd); PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                            thirdBaseDot.setVisible(true);
                            secondBaseDot.setVisible(false);
                        }
                        awayNextBatter();
                    } else {
                        if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                            thirdBaseDot.setVisible(true);
                            secondBaseDot.setVisible(false);
                        }
                        homeNextBatter();
                    }
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runner holds; E-SS; If 2 outs, runner out at 3rd; A-SS PO-3B");
                if (out == 2) {
                    threeOuts();
                } else {
                    firstBaseDot.setVisible(true);
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 19:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("Out at 1st; runner to 3rd; A-2B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 21:
                situationOutcome.setText("SINGLE to deep 1st; runner to 3rd");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Runner steals 3rd (S holds 2nd)");
                if(!secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                }
                break;
            case 23:
                situationOutcome.setText("Runner out stealing 3rd A-C PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                }
                break;
            case 24:
                situationOutcome.setText("Pop fly out; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; line drive; PO-SS A-SS PO-2B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Out at 1st; runner to 3rd; A-2B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runner holds; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; runner holds; A-SS PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runner to 3rd; A-1B PO-P");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runner holds; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runner holds; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runner holds; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Pop fly out; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("High fly out; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-3B (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch; runner to 3rd");
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                break;
            case 37:
                situationOutcome.setText("Double play; fly out; runner out trying for 3rd; PO-RF A-RF PO-3B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 38:
                situationOutcome.setText("SINGLE; line drive hits pitcher; runner to 3rd");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 39:
                situationOutcome.setText("Runner out stealing 3rd; A-C PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                }
                break;
            case 40:
                situationOutcome.setText("Safe at 1st; FC; runner out at 3rd; A-2B PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                secondBaseDot.setVisible(false);
                if(out == 3) {
                    threeOuts();
                } else {
                    firstBaseDot.setVisible(true);
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 41:
                situationOutcome.setText("Runner picked off 2nd; A-C PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingTwoManOnSecond(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("1st on error; runner scores; E-LF");
                firstBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    homeScore++;
                    updateHomeScore();
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("SINGLE to center; runner out at home; batter to 2nd on throw; A-CF PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("1st on error; runner to 3rd; (F scores) E-RF; if 2 outs, any runner scores");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    }
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F") || out == 2) {
                        homeScore++;
                        updateHomeScore();
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    }
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("Out at 1st; runner to 3rd (F scores); A-SS PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        if (teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        if (teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 19:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("1st and 2nd on error; runner scores; E-2B");
                if (teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    homeScore++;
                    updateHomeScore();
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("SINGLE to deep 1st; runner to 3rd");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Runner steals 3rd (S holds 2nd)");
                if(!secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                }
                break;
            case 23:
                situationOutcome.setText("Runner out stealing 3rd A-C PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                }
                break;
            case 24:
                situationOutcome.setText("Pop fly out; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Line drive out; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Out at 1st; runner to 3rd; A-2B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runner holds; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; runner holds; A-SS PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runner to 3rd; A-1B PO-P");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runner holds; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runner holds; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runner holds (F to 3rd); PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                        secondBaseDot.setVisible(false);
                    }
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("High fly out; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Pop fly out; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-3B (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch; runner to 3rd");
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                break;
            case 37:
                situationOutcome.setText("Runner caught off 2nd; A-P PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                }
                break;
            case 38:
                situationOutcome.setText("1st on error; runner holds; E-SS; If 2 outs, runner to 3rd");
                firstBaseDot.setVisible(true);
                if(out == 2) {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                }
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 39:
                situationOutcome.setText("Runner out stealing 3rd; A-C PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                }
                break;
            case 40:
                situationOutcome.setText("Fly out to RF; runner to 3rd on catch; PO-RF (S out at 3rd, DP; A-RF PO-3B)");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseDot.setVisible(false);
                            if(teamAtBat == 0) {
                                awayNextBatter();
                            } else {
                                homeNextBatter();
                            }
                        }
                    } else {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                        secondBaseDot.setVisible(false);
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 41:
                situationOutcome.setText("Runner steals 3rd; goes home on wild throw by catcher; E-C");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingThreeManOnSecond(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("1st on error; runner scores; E-LF");
                firstBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    homeScore++;
                    updateHomeScore();
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("SINGLE to center; runner scores; batter to 2nd on throw; A-CF PO-C");
                if (teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 17:
                situationOutcome.setText("Fly out; runner holds (F to 3rd); PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                            thirdBaseDot.setVisible(true);
                            secondBaseDot.setVisible(false);
                        }
                        awayNextBatter();
                    } else {
                        if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                            thirdBaseDot.setVisible(true);
                            secondBaseDot.setVisible(false);
                        }
                        homeNextBatter();
                    }
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runner holds; E-SS; if 2 outs, runner to 3rd");
                firstBaseDot.setVisible(true);
                if(out == 2) {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                }
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("1st and 2nd on error; runner scores; E-2B");
                if (teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    homeScore++;
                    updateHomeScore();
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("SINGLE to deep 1st; runner to 3rd");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Runner steals 3rd (S holds 2nd)");
                if(!secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                }
                break;
            case 23:
                situationOutcome.setText("Runner out stealing 3rd A-C PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                }
                break;
            case 24:
                situationOutcome.setText("Pop fly out; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Line drive out; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Out at 1st; runner to 3rd; A-2B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runner holds; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; runner to 3rd; A-SS PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runner to 3rd; A-1B PO-P");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runner holds; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runner holds; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runner to 3rd; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Pop fly out; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("High fly out; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-3B (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch; runner to 3rd");
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                break;
            case 37:
                situationOutcome.setText("Safe at 1st; runner out; FC; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    firstBaseDot.setVisible(true);
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 38:
                situationOutcome.setText("1st on error; runner holds; E-SS; If 2 outs, runner to 3rd");
                firstBaseDot.setVisible(true);
                if(out == 2) {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                }
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 39:
                situationOutcome.setText("Runner out stealing 3rd; A-C PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                }
                break;
            case 40:
                situationOutcome.setText("Catcher fumbles plate bouncer; batter safe at 1st; runner to 3rd; E-C");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 41:
                situationOutcome.setText("Runner steals 3rd; goes home on wild throw by catcher; E-C");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeAManOnThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE along left field foul line");
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("HOMERUN over right center field fence");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("DOUBLE to right; runner scores; batter out trying for 3rd; A-RF A-2B PO-3B");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 5:
                situationOutcome.setText("DOUBLE to right; runner scores");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("HOMERUN to left field");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("Fly out; runner scores; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE over 2nd; runner scores");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 9:
                situationOutcome.setText("Fly out; runner scores; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE to right; runner scores; batter steals 2nd on third pitch; one ball, " +
                        "two strikes on batter");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                strike += 2;
                ball++;
                strikesNum.setText(Integer.toString(strike));
                ballsNum.setText(Integer.toString(ball));
                break;
            case 11:
                situationOutcome.setText("SINGLE to center; runner scores; batter steals 2nd on first pitch; one " +
                        "strike on batter");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                strike++;
                strikesNum.setText(Integer.toString(strike));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeBManOnThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE along left field foul line");
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("HOMERUN over right center field fence");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("DOUBLE to right; runner scores; batter out trying for 3rd; A-RF A-2B PO-3B");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 5:
                situationOutcome.setText("DOUBLE to right; runner scores");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("HOMERUN to left field");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to left; runner scores");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("Fly out; runner scores; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE thru short; runner scores; batter out trying for 2nd; A-LF PO-SS");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE to right; runner scores; batter steals 2nd on third pitch; one ball, " +
                        "two strikes on batter");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                strike += 2;
                ball++;
                strikesNum.setText(Integer.toString(strike));
                ballsNum.setText(Integer.toString(ball));
                break;
            case 11:
                situationOutcome.setText("SINGLE to center; runner scores; batter steals 2nd on first pitch; one " +
                        "strike on batter");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                strike++;
                strikesNum.setText(Integer.toString(strike));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeCManOnThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE along left field foul line");
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("HOMERUN over right center field fence");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("DOUBLE to right; runner scores; batter out trying for 3rd; A-RF A-2B PO-3B");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 5:
                situationOutcome.setText("DOUBLE to right; runner scores");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("HOMERUN to left field");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to left; runner scores");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE over 2nd; runner scores");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 9:
                situationOutcome.setText("Fly out; runner scores; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE to right; runner scores; batter steals 2nd on third pitch; one ball, " +
                        "two strikes on batter");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                strike += 2;
                ball++;
                strikesNum.setText(Integer.toString(strike));
                ballsNum.setText(Integer.toString(ball));
                break;
            case 11:
                situationOutcome.setText("SINGLE to center; runner scores; batter steals 2nd on first pitch; one " +
                        "strike on batter");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                strike++;
                strikesNum.setText(Integer.toString(strike));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeDManOnThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE along left field foul line");
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("HOMERUN over right center field fence");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("DOUBLE to right; runner scores; batter out trying for 3rd; A-RF A-2B PO-3B");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 5:
                situationOutcome.setText("DOUBLE to right; runner scores");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("HOMERUN to left field");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to left; runner scores");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE over 2nd; runner scores");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE thru short; runner scores; batter out trying for 2nd; A-LF PO-SS");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE to right; runner scores; batter steals 2nd on third pitch; one ball, " +
                        "two strikes on batter");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                strike += 2;
                ball++;
                strikesNum.setText(Integer.toString(strike));
                ballsNum.setText(Integer.toString(ball));
                break;
            case 11:
                situationOutcome.setText("SINGLE to center; runner scores; batter steals 2nd on first pitch; one " +
                        "strike on batter");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                strike++;
                strikesNum.setText(Integer.toString(strike));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingOneManOnThird(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; fly out; runner out at home; PO-RF A-RF PO-C");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("1st and 2nd on error; runner scores; E-LF");
                secondBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 17:
                situationOutcome.setText("1st on error; runner scores; E-RF");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runner scores; E-SS");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("FC; runner escapes rundown on dropped throw; batter out at 2nd; runner " +
                        "remains at 3rd; A-3B A-C E-3B A-3B PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 20:
                situationOutcome.setText("SINGLE to 2nd; Grade C Pitcher-runner holds (F scores); Grade D Pitcher-" +
                        "runner scores");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(homePitcherGrade.equalsIgnoreCase("C")) {
                        if(thirdBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            thirdBaseDot.setVisible(false);
                            awayScore++;
                            updateAwayScore();
                        }
                    } else if (homePitcherGrade.equalsIgnoreCase("D")) {
                        thirdBaseDot.setVisible(false);
                        awayScore++;
                        updateAwayScore();
                    }
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(awayPitcherGrade.equalsIgnoreCase("C")) {
                        if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            thirdBaseDot.setVisible(false);
                            homeScore++;
                            updateHomeScore();
                        }
                    } else if (awayPitcherGrade.equalsIgnoreCase("D")) {
                        thirdBaseDot.setVisible(false);
                        homeScore++;
                        updateHomeScore();
                    }
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("Out at 1st; runner scores; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 22:
                situationOutcome.setText("Safe at 1st; FC; runner scores on error; A-SS E-C");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 23:
                situationOutcome.setText("SINGLE - pop fly falls safe; runner scores; If 2 outs, Balk and runner scores" +
                        "");
                thirdBaseDot.setVisible(false);
                if(out != 2) {
                    firstBaseDot.setVisible(true);
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                }
                break;
            case 24:
                situationOutcome.setText("Double play; fly out; runner out trying for home; PO-SS A-SS PO-C");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play - line drive; PO-2B A-2B PO-3B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("SINGLE thru 2nd; runner scores; (X - out at 1st; runner scores; A-2B PO-1B)");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        }
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runner holds; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Safe at 1st; runner out at home; A-SS PO-C (X-Out at 1st runner scores; A-SS " +
                        "PO-1B)");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        if(homePitcherRating.equalsIgnoreCase("X")) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            firstBaseDot.setVisible(true);
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        if(awayPitcherRating.equalsIgnoreCase("X")) {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        } else {
                            firstBaseDot.setVisible(true);
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runner holds; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runner holds; PO-LF (F scores)");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(thirdBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        thirdBaseDot.setVisible(false);
                        if (teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    } else {
                        if (teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runner holds; PO-CF (F scores)");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(thirdBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        thirdBaseDot.setVisible(false);
                        if (teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    } else {
                        if (teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runner holds; PO-RF (F scores)");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(thirdBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        thirdBaseDot.setVisible(false);
                        if (teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    } else {
                        if (teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Bunt fly out; PO-P");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("High infield fly out; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-SS (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        firstBaseDot.setVisible(true);
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        firstBaseDot.setVisible(true);
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch; runner scores");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 37:
                situationOutcome.setText("Fly out; runner scores; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Fly out; runner scores; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Fly out; runner scores; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 40:
                situationOutcome.setText("Fly out; runner scores; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 41:
                situationOutcome.setText("Runner steals home; (X-runner caught off 3rd; A-C PO-3B)");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            playerAtBatAway--;
                            threeOuts();
                        }
                    } else {
                        awayScore++;
                        updateAwayScore();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            playerAtBatHome--;
                            threeOuts();
                        }
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingTwoManOnThird(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; fly out; runner out at home; PO-RF A-RF PO-C");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("SINGLE to left; runner scores; batter to 2nd on wild throw; E-LF");
                secondBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 17:
                situationOutcome.setText("Fly out; runner scores after catch; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runner scores; E-RF");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; runner scores; E-SS");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("SINGLE to 2nd; Grade C Pitcher-runner holds (F scores); Grade D Pitcher-" +
                        "runner scores");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(homePitcherGrade.equalsIgnoreCase("C")) {
                        if(thirdBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            thirdBaseDot.setVisible(false);
                            awayScore++;
                            updateAwayScore();
                        }
                    } else if (homePitcherGrade.equalsIgnoreCase("D")) {
                        thirdBaseDot.setVisible(false);
                        awayScore++;
                        updateAwayScore();
                    }
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(awayPitcherGrade.equalsIgnoreCase("C")) {
                        if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                            thirdBaseDot.setVisible(false);
                            homeScore++;
                            updateHomeScore();
                        }
                    } else if (awayPitcherGrade.equalsIgnoreCase("D")) {
                        thirdBaseDot.setVisible(false);
                        homeScore++;
                        updateHomeScore();
                    }
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("Out at 1st; runner scores; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 22:
                situationOutcome.setText("Safe at 1st; FC; runner scores on error; A-SS E-C");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 23:
                situationOutcome.setText("SINGLE - pop fly falls safe; runner scores; If 2 outs, Balk and runner scores" +
                        "");
                thirdBaseDot.setVisible(false);
                if(out != 2) {
                    firstBaseDot.setVisible(true);
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                }
                break;
            case 24:
                situationOutcome.setText("Batter safe at 1st; FC; runner out at home; A-SS PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    firstBaseDot.setVisible(true);
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play - line drive; both PO-3B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Runner out at home; batter to 2nd on rundown; A-2B A-C PO-3B;" +
                        " (X-out at 1st; runner scores; A-2B PO-1B)");
                thirdBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        if(homePitcherRating.equalsIgnoreCase("X")) {
                            awayScore++;
                            updateAwayScore();
                        } else {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        }
                        awayNextBatter();
                    } else {
                        if(awayPitcherRating.equalsIgnoreCase("X")) {
                            homeScore++;
                            updateHomeScore();
                        } else {
                            secondBaseDot.setVisible(true);
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        }
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runner holds; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("SINGLE thru shortstop; runner scores; (X - out at 1st; runner scores; A-SS PO-1B)");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        }
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runner holds; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runner scores; PO-LF (S out at home; DP; A-LF PO-C)");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(thirdBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        }
                    }
                } else {
                    if(thirdBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runner scores; PO-CF (S out at home; DP; A-CF PO-C)");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(thirdBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        }
                    }
                } else {
                    if(thirdBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runner holds; PO-RF (F scores)");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(thirdBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        thirdBaseDot.setVisible(false);
                        if(teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Short pop fly; PO-P");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Pop fly out; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("High fly out behind 3rd base; PO-SS (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        firstBaseDot.setVisible(true);
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        firstBaseDot.setVisible(true);
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch; runner scores");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 37:
                situationOutcome.setText("Fly out; runner scores; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Fly out; runner scores; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Fly out; runner scores; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 40:
                situationOutcome.setText("Fly out; runner scores; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 41:
                situationOutcome.setText("Runner steals home; (X-runner caught off 3rd; A-C PO-3B)");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            playerAtBatAway--;
                            threeOuts();
                        }
                    } else {
                        awayScore++;
                        updateAwayScore();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            playerAtBatHome--;
                            threeOuts();
                        }
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingThreeManOnThird(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; fly out; runner out at home; PO-RF A-RF PO-C");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("SINGLE to left; runner scores; batter to 2nd on throw home; E-LF");
                secondBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 17:
                situationOutcome.setText("1st on error; runner scores; E-RF");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runner scores; E-SS");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; runner scores; E-3B");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; runner scores; E-2B");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("1st & 2nd on error; runner scores; E-1B");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Safe at 1st; FC; runner scores on error; A-SS E-C");
                firstBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 23:
                situationOutcome.setText("SINGLE - pop fly falls safe; runner scores; If 2 outs, Balk and runner scores" +
                        "");
                thirdBaseDot.setVisible(false);
                if(out != 2) {
                    firstBaseDot.setVisible(true);
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                }
                break;
            case 24:
                situationOutcome.setText("Safe at 1st; FC; runner out at home; A-SS A-C PO-3B; batter to 2nd on rundown");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(true);
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Line drive out; PO-P");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("SINGLE thru shortstop; runner scores; (X - out at 1st; runner scores; A-SS PO-1B)");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        }
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    } else {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runner holds; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; runner holds; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runner holds; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runner scores; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                thirdBaseDot.setVisible(false);
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runner scores; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                thirdBaseDot.setVisible(false);
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runner scores; PO-LF (S out at home; DP; A-LF PO-C)");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(thirdBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        }
                    }
                } else {
                    if(thirdBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Fly out; popup; PO-P");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Pop fly out; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-SS (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        firstBaseDot.setVisible(true);
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        firstBaseDot.setVisible(true);
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch; runner scores");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 37:
                situationOutcome.setText("Fly out; runner scores; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Fly out; runner scores; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Fly out; runner scores; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 40:
                situationOutcome.setText("Safe at 1st on error; runner holds; shortstop drops pop fly; E-SS; If 2 outs" +
                        ", then runner scores");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    if(out == 2) {
                        thirdBaseDot.setVisible(false);
                        awayScore++;
                        updateAwayScore();
                    }
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    if(out == 2) {
                        thirdBaseDot.setVisible(false);
                        homeScore++;
                        updateHomeScore();
                    }
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 41:
                situationOutcome.setText("Runner steals home; (X-runner caught off 3rd; A-C PO-3B)");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            playerAtBatAway--;
                            threeOuts();
                        }
                    } else {
                        awayScore++;
                        updateAwayScore();
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("X")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            playerAtBatHome--;
                            threeOuts();
                        }
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; batter takes 1st");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeAManOnFirstAndSecond(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("HOMERUN to extreme right field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to deep center; runners score; (S on 1st out at home; A-CF A-SS PO-C)");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        awayScore++;
                        updateAwayScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        homeScore++;
                        updateHomeScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("TRIPLE to right field bull pen");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to left center; runners score; (S on 1st out at home; A-LF A-SS PO-C)");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        awayScore++;
                        updateAwayScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        homeScore++;
                        updateHomeScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to left; one scores(S out at home at home & batter to 2nd; A_LF PO-C); " +
                        "other to 3rd");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(false);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(false);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 8:
                situationOutcome.setText("Out at 1st, runners advance one base; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("Out at 1st, runners advance one base; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("Out at 1st, runners advance one base; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE over 2nd; one runner scores; other 3rd; batter steals 2nd on next " +
                        "pitch; one ball on batter");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                } else {
                    homeScore++;
                    updateHomeScore();
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeBManOnFirstAndSecond(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("HOMERUN to extreme right field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to deep center; runners score; (S on 1st out at home; A-CF A-SS PO-C)");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        awayScore++;
                        updateAwayScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        homeScore++;
                        updateHomeScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("TRIPLE to right field bull pen");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to left center; runners score; (S on 1st out at home; A-LF A-SS PO-C)");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        awayScore++;
                        updateAwayScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        homeScore++;
                        updateHomeScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to left; one scores(S out at home at home & batter to 2nd; A_LF PO-C); " +
                        "other to 3rd");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(false);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(false);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 8:
                situationOutcome.setText("Out at 1st, runners advance one base; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE - beats out infield hit; fills the bases");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 10:
                situationOutcome.setText("Out at 1st, runners advance one base; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE over 2nd; one runner scores; other 3rd; batter steals 2nd on next " +
                        "pitch; one ball on batter");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                } else {
                    homeScore++;
                    updateHomeScore();
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeCManOnFirstAndSecond(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("HOMERUN to extreme right field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to deep center; runners score; (S on 1st out at home; A-CF A-SS PO-C)");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        awayScore++;
                        updateAwayScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        homeScore++;
                        updateHomeScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("TRIPLE to right field bull pen");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to left center; runners score; (S on 1st out at home; A-LF A-SS PO-C)");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        awayScore++;
                        updateAwayScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        homeScore++;
                        updateHomeScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to left; one scores(S out at home at home & batter to 2nd; A_LF PO-C); " +
                        "other to 3rd");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(false);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(false);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE over short; one scores; other to 2nd; If 2 outs, other to 3rd");
                if(out == 2) {
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                } else {
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                }
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 9:
                situationOutcome.setText("Out at 1st, runners advance one base; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE - beats out bunt; fills the bases");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE over 2nd; one runner scores; other 3rd; batter steals 2nd on next " +
                        "pitch; one ball on batter");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                } else {
                    homeScore++;
                    updateHomeScore();
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeDManOnFirstAndSecond(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("HOMERUN to extreme right field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to deep center; runners score; (S on 1st out at home; A-CF A-SS PO-C)");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        awayScore++;
                        updateAwayScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        homeScore++;
                        updateHomeScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("TRIPLE to right field bull pen");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to left center; runners score; (S on 1st out at home; A-LF A-SS PO-C)");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        awayScore++;
                        updateAwayScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    } else {
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        homeScore++;
                        updateHomeScore();
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    } else {
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to left; one scores(S out at home at home & batter to 2nd; A_LF PO-C); " +
                        "other to 3rd");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            firstBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(false);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            firstBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    } else {
                        secondBaseDot.setVisible(false);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE over short; one scores; other to 2nd; If 2 outs, other to 3rd");
                if(out == 2) {
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                } else {
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                }
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE - beats out infield hit; fills the bases");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE - beats out bunt; fills the bases");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE over 2nd; one runner scores; other 3rd; batter steals 2nd on next " +
                        "pitch; one ball on batter");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                } else {
                    homeScore++;
                    updateHomeScore();
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                    ball++;
                    ballsNum.setText(Integer.toString(ball));
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingOneManOnFirstAndSecond(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; A-3B PO-2B A-2B PO-1B; runner to 3rd (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            thirdBaseDot.setVisible(true);
                            thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                            firstBaseDot.setVisible(false);
                            secondBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            thirdBaseDot.setVisible(true);
                            thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                            firstBaseDot.setVisible(false);
                            secondBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; fills the bases");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("SINGLE to left; one runner scores; other to 3rd; batter out trying for 2nd; " +
                        "A-LF A-1B PO-2B");
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 16:
                situationOutcome.setText("1st on error; runner out at home; other to 3rd; batter to 2nd on " +
                        "throw home; E-CF A-CF PO-P");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("Hit by pitcher; fills the bases");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("SINGLE thru short; runner advance 2 bases; (S on 1st out at 3rd; A-CF PO-3B)");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        }
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; runners advance 2 bases; E-3B");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; filling the bases; E-2B");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("Out at 1st; runners advance 1 base; A-3B PO-1B");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 22:
                situationOutcome.setText("1st on error; filling the bases; E-P");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 23:
                situationOutcome.setText("Foul; strike");
                strike++;
                if(strike > 2) {
                    strike = 2;
                }
                strikesNum.setText(Integer.toString(strike));
                break;
            case 24:
                situationOutcome.setText("Double play; one runner to 3rd; other out at 2nd; A-2B PO-SS A-SS PO-1B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; one runner to 3rd; other out at 2nd; A-SS PO-2B A-2B PO-1B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Infield fly; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Double play; one runner to 2nd; other out at 3rd; PO-3B A-3B PO-1B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Double play; runner to 3rd; A-SS PO-2B A-2B PO-1B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Double play; one runner to 3rd; A-P PO-SS A-SS PO-1B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runners hold; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runners hold; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners hold; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Infield fly; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Infield fly; PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-C (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        thirdBaseDot.setVisible(true);
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        thirdBaseDot.setVisible(true);
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Passed ball; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                break;
            case 37:
                situationOutcome.setText("Double steal; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                break;
            case 38:
                situationOutcome.setText("Out at 1st; runners advance one base; A-1B PO-P");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Double play; fly out; PO-CF; runner out at 3rd; other holds; A-CF PO-3B " +
                        "(F safe at 3rd)");
                if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseDot.setVisible(false);
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                } else {
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        secondBaseDot.setVisible(false);
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 40:
                situationOutcome.setText("Safe on error; filling the bases; A-SS E-2B");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 41:
                situationOutcome.setText("Triple play; line drive; PO-2B PO-2B A-2B PO-1B");
                threeOuts();
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; fills the bases");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingTwoManOnFirstAndSecond(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; A-3B PO-2B A-2B PO-1B; runner to 3rd (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            thirdBaseDot.setVisible(true);
                            thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                            firstBaseDot.setVisible(false);
                            secondBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            thirdBaseDot.setVisible(true);
                            thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                            firstBaseDot.setVisible(false);
                            secondBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; fills the bases");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("SINGLE to left; one runner scores; other to 3rd & batter to 2nd on throw " +
                        "home");
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("Fly out; runners advance one base; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("Hit by pitcher; fills the bases");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("SINGLE thru short; runners advance 1 base; (S on 1st out at 3rd; A-CF PO-3B)");
                if(teamAtBat == 0) {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; runners advance 1 base; E-3B");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; filling the bases; E-2B");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("1st on error; filling the bases; A-3B E-1B");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("1st on error; filling the bases; E-P");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 23:
                situationOutcome.setText("Ball");
                ball++;
                ballsNum.setText(Integer.toString(ball));
                if(ball == 4) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 24:
                situationOutcome.setText("Double play; one runner to 3rd; other out at 2nd; A-2B PO-SS A-SS PO-1B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; one runner to 3rd; other out at 2nd; A-SS PO-2B A-2B PO-1B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Infield fly; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Double play; one runner to 3rd; other out at 2nd; A-3B PO-2B A-2B PO-1B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Batter safe at 1st; one runner out at 2nd; other safe at 3rd; A-SS PO-2B; FC");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Double play; one runner to 3rd; A-P PO-SS A-SS PO-1B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runners hold; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runners hold; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners hold (F on 2nd to 3rd); PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        secondBaseDot.setVisible(false);
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    }
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Infield fly; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Infield fly; PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-C (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        thirdBaseDot.setVisible(true);
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        thirdBaseDot.setVisible(true);
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Passed ball; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                break;
            case 37:
                situationOutcome.setText("Double steal; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                break;
            case 38:
                situationOutcome.setText("Safe at 1st; one runner to 3rd; other out at 2nd; A-3B PO-2B; FC");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Double play; one runner out at 3rd; other to 2nd; PO-3B A-3B PO-1B");
                firstBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 40:
                situationOutcome.setText("1st on error; filling the bases; E-2B");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 41:
                situationOutcome.setText("Triple play; line drive; PO-2B PO-2B A-2B PO-1B");
                threeOuts();
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; fills the bases");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingThreeManOnFirstAndSecond(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; A-3B PO-2B A-2B PO-1B; runner to 3rd (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            thirdBaseDot.setVisible(true);
                            thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                            firstBaseDot.setVisible(false);
                            secondBaseDot.setVisible(false);
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        thirdBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out += 2;
                        outsNum.setText(Integer.toString(out));
                        if(out >= 3) {
                            threeOuts();
                        } else {
                            thirdBaseDot.setVisible(true);
                            thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                            firstBaseDot.setVisible(false);
                            secondBaseDot.setVisible(false);
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; fills the bases");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("SINGLE to left; one runner scores; other to 3rd & batter to 2nd on throw " +
                        "home");
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("1st on error; runner out at home; other to 3rd; batter to 2nd on throw home; " +
                        "E-CF A-CF PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("Hit by pitcher; fills the bases");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("SINGLE thru short; runner advance 2 bases; (S on 1st out at 3rd; A-CF PO-3B)");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        }
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    }
                } else {
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    } else {
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; runners advance 2 bases; E-3B");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; filling the bases; E-2B");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("1st on error; filling the bases; A-3B E-1B");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("1st on error; filling the bases; E-P");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 23:
                situationOutcome.setText("Ball");
                ball++;
                ballsNum.setText(Integer.toString(ball));
                if(ball == 4) {
                    thirdBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 24:
                situationOutcome.setText("Double play; one runner to 3rd; other out at 2nd; A-2B PO-SS A-SS PO-1B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; one runner to 3rd; other out at 2nd; A-SS PO-2B A-2B PO-1B");
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Infield fly; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runners advance 1 base; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Batter safe at 1st; one runner out at 2nd; other safe at 3rd; A-SS PO-2B; FC");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    thirdBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runners advance 1 base; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runners hold; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runners hold (F on 2nd to 3rd); PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        secondBaseDot.setVisible(false);
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    }
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners hold (F on 2nd to 3rd); PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        secondBaseDot.setVisible(false);
                        thirdBaseDot.setVisible(true);
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    }
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Infield fly; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Infield fly; PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-C (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        thirdBaseDot.setVisible(true);
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        thirdBaseDot.setVisible(true);
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Passed ball; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                break;
            case 37:
                situationOutcome.setText("Double steal; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                break;
            case 38:
                situationOutcome.setText("Out at 1st; runners advance one base; A-1B PO-P");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                thirdBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Double play; one runner to 3rd; A-3B PO-2B A-2B PO-1B");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 40:
                situationOutcome.setText("1st on error; filling the bases; E-2B");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 41:
                situationOutcome.setText("Triple play; line drive; PO-2B PO-2B A-2B PO-1B");
                threeOuts();
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; fills the bases");
                thirdBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeAManOnFirstAndThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme right field");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("TRIPLE over third");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to left");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to center; clears the bases");
                secondBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to left; one runner scores; other to 3rd; (S out at 3rd; A-LF PO-3B)");
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        thirdBaseDot.setVisible(false);
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    homeScore++;
                    updateHomeScore();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        thirdBaseDot.setVisible(false);
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 8:
                situationOutcome.setText("Fly out; one runner scores; other holds; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("Fly out; one runner scores; other holds; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("Fly out; one runner scores; other holds; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; one runner scores; other to 2nd (F to 3rd)");
                if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                } else {
                    thirdBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseDot.setVisible(true);
                }
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeBManOnFirstAndThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme right field");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("TRIPLE over third");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to left");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to center; clears the bases");
                secondBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("Fly out; one runner scores; other holds; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE to right; one runner scores; other to 3rd; (S out at 3rd; A-LF PO-3B)");
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        thirdBaseDot.setVisible(false);
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    homeScore++;
                    updateHomeScore();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        thirdBaseDot.setVisible(false);
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE over short; one runner scores; other to 2nd");
                thirdBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE over 2nd; one runner scores; other out at 3rd; batter to 2nd; A-CF " +
                        "PO-3B");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    homeScore++;
                    updateHomeScore();
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; one runner scores; other to 2nd (F to 3rd)");
                if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                } else {
                    thirdBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseDot.setVisible(true);
                }
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeCManOnFirstAndThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme right field");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("TRIPLE over third");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to left");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to center; clears the bases");
                secondBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to right; one runner scores; other to 3rd; (S out at 3rd; A-LF PO-3B)");
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        thirdBaseDot.setVisible(false);
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    homeScore++;
                    updateHomeScore();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        thirdBaseDot.setVisible(false);
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 8:
                situationOutcome.setText("Fly out; one runner scores; other holds; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE over short; one runner scores; other to 2nd");
                thirdBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE over 2nd; one runner scores; other out at 3rd; batter to 2nd; A-CF " +
                        "PO-3B");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    homeScore++;
                    updateHomeScore();
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; one runner scores; other to 2nd (F to 3rd)");
                if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                } else {
                    thirdBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseDot.setVisible(true);
                }
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeDManOnFirstAndThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over left field fence");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme right field");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("TRIPLE over third");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("TRIPLE to left");
                firstBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("HOMERUN over right field fence");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to center; clears the bases");
                secondBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to right; one runner scores; other to 3rd; (S out at 3rd; A-LF PO-3B)");
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        thirdBaseDot.setVisible(false);
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    homeScore++;
                    updateHomeScore();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        thirdBaseDot.setVisible(false);
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE to right; one runner scores; other to 3rd; (S out at 3rd; A-RF PO-3B)");
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        thirdBaseDot.setVisible(false);
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        }
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    homeScore++;
                    updateHomeScore();
                    if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                        thirdBaseDot.setVisible(false);
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    } else {
                        thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE over short; one runner scores; other to 2nd");
                thirdBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE over 2nd; one runner scores; other out at 3rd; batter to 2nd; A-CF " +
                        "PO-3B");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    }
                } else {
                    homeScore++;
                    updateHomeScore();
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; one runner scores; other to 2nd (F to 3rd)");
                if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                } else {
                    thirdBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseDot.setVisible(true);
                }
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingOneManOnFirstAndThird(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; A-1B PO-SS A-SS PO-1B; runner on 3rd scores, if this does " +
                        "not make third out");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; filling the bases");
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("Fly out; one scores; other holds; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 16:
                situationOutcome.setText("Fly out; one scores; other holds; PO-CF (S out at home, A-CF PO-C; DP; " +
                        "other to 2nd on throw)");
                thirdBaseDot.setVisible(false);
                if(thirdBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if (teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; one runner scores; other scores and batter to 2nd on " +
                        "error; E-RF");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; one scores; other to 3rd; E-3B");
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("SINGLE thru 2nd; one runner scores; other out at 3rd; batter to 2nd on throw;" +
                        " A-RF PO-3B (F safe at 3rd)");
                firstBaseDot.setVisible(false);
                if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    secondBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    secondBaseDot.setVisible(true);
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 21:
                situationOutcome.setText("Ball-catcher throws wild to 3rd; one runner scores; other to 2nd; E-C");
                ball++;
                ballsNum.setText(Integer.toString(ball));
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 22:
                situationOutcome.setText("Balk; runners advance 1 base");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 23:
                situationOutcome.setText("Game called because of rain");
                roll.setDisable(true);
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                break;
            case 24:
                situationOutcome.setText("Double play; A-2B PO-SS A-SS PO-1B; runner scores, if not third out");
                firstBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; A-SS PO-2B A-2B PO-1B; runner scores, if not third out");
                firstBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Out at 1st; runner holds 3rd; other to 2nd; A-2B PO-1B");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Double play; one runner holds 3rd; other out at 2nd; A-3B PO-2B A-2B PO-1B");
                firstBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("SINGLE to left; one runner scores; other to 2nd");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runner to 2nd; other holds; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runners hold; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; one scores; other holds; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners hold; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Pop fly out; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("High fly out; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out in short right field; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Runner on 1st steals 2nd; other holds 3rd");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                break;
            case 37:
                situationOutcome.setText("Runner on 1st out stealing; A-C PO-SS; other holds");
                firstBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                }
                break;
            case 38:
                situationOutcome.setText("Runner on 1st steals 2nd; other holds 3rd (S runner on 1st holds)");
                if(!firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                }
                break;
            case 39:
                situationOutcome.setText("Runner on 3rd picked off; A-C PO-3B; other to 2nd");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                }
                break;
            case 40:
                situationOutcome.setText("Runner out stealing home; other to 2nd; A-C A-2B PO-2B");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                }
                break;
            case 41:
                situationOutcome.setText("Double steal; one runner scores; other to 2nd");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingTwoManOnFirstAndThird(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; A-1B PO-SS A-SS PO-1B; runner on 3rd scores, if this does " +
                        "not make third out");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; filling the bases");
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("1st on error; runners advance one base; If 2 outs, runners advance 2 bases;" +
                        " E-LF");
                if(out == 2) {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 16:
                situationOutcome.setText("Fly out; one scores; other holds; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; one runner scores; other scores and batter to 2nd on " +
                        "error; E-RF");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; one scores; other to 3rd; E-3B");
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; one runner scores; other out at 3rd; batter to 2nd on throw;" +
                        " A-RF PO-3B (F safe at 3rd)");
                firstBaseDot.setVisible(false);
                if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    secondBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    secondBaseDot.setVisible(true);
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 21:
                situationOutcome.setText("Strikeout; PO-C; If 2 outs, catcher drops third strike; batter safe at first;" +
                        " filling the bases; passed ball");
                if(out == 2) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 22:
                situationOutcome.setText("Balk; runners advance 1 base");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 23:
                situationOutcome.setText("Game called because of rain");
                roll.setDisable(true);
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                break;
            case 24:
                situationOutcome.setText("Double play; A-2B PO-SS A-SS PO-1B; runner scores, if not third out");
                firstBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; A-SS PO-2B A-2B PO-1B; runner scores, if not third out");
                firstBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("SINGLE to right; one runner scores; other to 2nd");
                secondBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runner to 2nd; other holds; A-3B PO-1B");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; runner holds 3rd; other to 2nd; A-3B PO-1B");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runner to 2nd; other holds; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; one scores; other holds; PO-LF (S out at home; A-LF PO-C; DP)");
                thirdBaseDot.setVisible(false);
                if(thirdBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runners hold; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners hold; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("High fly out; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Pop fly out; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out back of 1st base; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Runner on 1st steals 2nd; other holds 3rd (S runner on 1st holds)");
                if(!firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                }
                break;
            case 37:
                situationOutcome.setText("Runner on 1st out stealing 2nd; A-C PO-SS; other scores");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Runner on 1st steals 2nd; other holds 3rd");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                break;
            case 39:
                situationOutcome.setText("Runner on 3rd picked off; A-C PO-3B; other to 2nd");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                }
                break;
            case 40:
                situationOutcome.setText("Runner out stealing home; other to 2nd; A-C A-2B PO-2B");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                }
                break;
            case 41:
                situationOutcome.setText("Double steal; one runner scores; other to 2nd");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingThreeManOnFirstAndThird(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; A-1B PO-SS A-SS PO-1B; runner on 3rd scores, if this does " +
                        "not make third out");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; filling the bases");
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("1st on error; runners advance one base; If 2 outs, runners advance 2 bases;" +
                        " E-LF");
                if(out == 2) {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 16:
                situationOutcome.setText("SINGLE to center; runners advance 1 base; (F on 1st advances to 3rd)");
                if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                } else {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    thirdBaseDot.setVisible(false);
                }
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; one runner scores; other to 2nd; other scores and batter " +
                        "to 2nd on wild throw; E-RF");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 18:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; one scores; other to 3rd; E-3B");
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("SINGLE thru 2nd; one runner scores; other out at 3rd; batter to 2nd on throw;" +
                        " A-RF PO-3B (F safe at 3rd)");
                firstBaseDot.setVisible(false);
                if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    secondBaseDot.setVisible(true);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    secondBaseDot.setVisible(true);
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 21:
                situationOutcome.setText("Strikeout; PO-C; If 2 outs, catcher drops third strike; batter safe at first;" +
                        " filling the bases; passed ball");
                if(out == 2) {
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 22:
                situationOutcome.setText("Balk; runners advance 1 base");
                thirdBaseDot.setVisible(false);
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 23:
                situationOutcome.setText("Game called because of rain");
                roll.setDisable(true);
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                break;
            case 24:
                situationOutcome.setText("Double play; A-2B PO-SS A-SS PO-1B; runner scores, if not third out");
                firstBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; A-SS PO-2B A-2B PO-1B; runner scores, if not third out");
                firstBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Out at 1st; runner holds 3rd; other to 2nd; A-2B PO-1B");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runner to 2nd; other holds; A-3B PO-1B");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("SINGLE to left; one runner scores; other to 2nd");
                secondBaseDot.setVisible(true);
                thirdBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runner to 2nd; other holds; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(true);
                    firstBaseDot.setVisible(false);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; one scores; other holds; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; one scores; other holds; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners hold; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Pop fly out; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("High fly out; PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Runner on 1st steals 2nd; other holds 3rd");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                break;
            case 37:
                situationOutcome.setText("Runner on 1st steals 2nd; other holds 3rd (S runner on 1st holds)");
                if(!firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                }
                break;
            case 38:
                situationOutcome.setText("Runner on 1st steals 2nd; other holds 3rd");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                break;
            case 39:
                situationOutcome.setText("Runner on 1st steals 2nd; other holds 3rd (S runner on 1st holds)");
                if(!firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    firstBaseDot.setVisible(false);
                    secondBaseDot.setVisible(true);
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                }
                break;
            case 40:
                situationOutcome.setText("Fly out; one runner scores; other holds; PO-CF (S out at home; " +
                        "A-CF PO-C; DP)");
                thirdBaseDot.setVisible(false);
                if(thirdBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 41:
                situationOutcome.setText("Double steal; one runner scores; other to 2nd");
                thirdBaseDot.setVisible(false);
                secondBaseDot.setVisible(true);
                firstBaseDot.setVisible(false);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                secondBaseDot.setVisible(true);
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeAManOnSecondAndThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over center field fence");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme left field");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to deep center; runners score; batter out trying for 3rd; A-CF A-2B " +
                        "PO-3B");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                }
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("HOMERUN over left field fence");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("TRIPLE to deep left");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to right; clears the bases");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("Fly out; one runner scores; other holds; PO-LF");
                thirdBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE over 1st; runners score; (S on 2nd out at home; A-RF PO-C; batter" +
                        " to 2nd)");
                thirdBaseDot.setVisible(false);
                if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        } else {
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    }
                } else {
                    firstBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("Fly out; PO-CF; one scores; other holds");
                thirdBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("Fly out; PO-RF; one scores; other holds");
                thirdBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; runners advance 1 base (If 2 outs, both score); batter " +
                        "steals 2nd; 1 ball and 1 strike on batter");
                if(out == 2) {
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                ball = 1;
                strike = 1;
                ballsNum.setText(Integer.toString(ball));
                strikesNum.setText(Integer.toString(strike));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeBManOnSecondAndThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over center field fence");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme left field");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to deep center; runners score; batter out trying for 3rd; A-CF A-2B " +
                        "PO-3B");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                }
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("HOMERUN over left field fence");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("TRIPLE to deep left");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to right; clears the bases");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to left center; runners score; batter to 2nd on throw home");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("Fly out; runners advance 1 base; PO-CF");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("Fly out; runners advance 1 base; PO-RF");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE over 3rd; one runner scores; other to 3rd; If 2 outs, both score");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if(out == 2) {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; runners advance 1 base (If 2 outs, both score); batter " +
                        "steals 2nd; 1 ball and 1 strike on batter");
                if(out == 2) {
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                ball = 1;
                strike = 1;
                ballsNum.setText(Integer.toString(ball));
                strikesNum.setText(Integer.toString(strike));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeCManOnSecondAndThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over center field fence");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme left field");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to deep center; runners score; batter out trying for 3rd; A-CF A-2B " +
                        "PO-3B");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                }
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("HOMERUN over left field fence");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("TRIPLE to deep left");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to right; clears the bases");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to left center; runners score; batter to 2nd on throw home");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE over 1st; runners score; (S on 2nd out at home; A-RF PO-C; batter" +
                        " to 2nd)");
                thirdBaseDot.setVisible(false);
                if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        } else {
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    }
                } else {
                    firstBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("Fly out; runners advance 1 base; PO-RF");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("Fly out; runners advance 1 base; PO-CF");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 11:
                situationOutcome.setText("SINGLE to left; runners advance 1 base (If 2 outs, both score); batter " +
                        "steals 2nd; 1 ball and 1 strike on batter");
                if(out == 2) {
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                ball = 1;
                strike = 1;
                ballsNum.setText(Integer.toString(ball));
                strikesNum.setText(Integer.toString(strike));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeDManOnSecondAndThird(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN over center field fence");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme left field");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("DOUBLE to deep center; runners score; batter out trying for 3rd; A-CF A-2B " +
                        "PO-3B");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                }
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 4:
                situationOutcome.setText("HOMERUN over left field fence");
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 5:
                situationOutcome.setText("TRIPLE to deep left");
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 6:
                situationOutcome.setText("DOUBLE to right; clears the bases");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE to left center; runners score; batter to 2nd on throw home");
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE over 1st; runners score; (S on 2nd out at home; A-RF PO-C; batter" +
                        " to 2nd)");
                thirdBaseDot.setVisible(false);
                if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        } else {
                            secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    }
                } else {
                    firstBaseDot.setVisible(true);
                    secondBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE beats out infield hit; one runner scores; other to 3rd");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE over 3rd; one runner scores; other to 3rd; If 2 outs, both score");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if(out == 2) {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
            case 11:
                situationOutcome.setText("SINGLE to left; runners advance 1 base (If 2 outs, both score); batter " +
                        "steals 2nd; 1 ball and 1 strike on batter");
                if(out == 2) {
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                ball = 1;
                strike = 1;
                ballsNum.setText(Integer.toString(ball));
                strikesNum.setText(Integer.toString(strike));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingOneManOnSecondAndThird(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; fly out; runner out at home; other to 3rd; PO-LF A-LF PO-C");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; filling the bases");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("1st on error; E-LF; runners advance 1 base; batter to 2nd on throw home; If" +
                        " 2 outs, both runners score");
                if(out == 2) {
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore ++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 16:
                situationOutcome.setText("Fly out to deep center field; PO-CF; both runners advance 1 base after " +
                        "the catch");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore ++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; one runner scores; other out at home; A-RF PO-C; batter" +
                        " to 2nd on throw home (F safe at home)");
                thirdBaseDot.setVisible(false);
                if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 18:
                situationOutcome.setText("Pop fly out; PO-SS; runners hold");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 19:
                situationOutcome.setText("SINGLE; runners advance 1 base");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("Pop fly out; PO-2B; runners hold");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 21:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("SINGLE - runner on 2nd hit by batted ball; other holds 3rd; PO-SS");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 23:
                situationOutcome.setText("Triple play; rightfielder makes shoestring catch; PO-RF A-RF PO-2B A-2B PO-3B");
                threeOuts();
                break;
            case 24:
                situationOutcome.setText("Double play; foul out; back of 1st; runner out trying for home; other " +
                        "to 3rd; PO-2B A-2B PO-C");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; line drive; runner doubled of 2nd; other holds 3rd; PO-1B " +
                        "A-1B PO-SS");
                secondBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("SINGLE thru 2nd; runners advance 1 base");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runners hold; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; runners hold; A-SS PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runners hold; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; one scores; other holds; PO-LF");
                thirdBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runners hold; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners hold; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Pop fly out; runners hold; PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("High fly out; runners hold; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-1B (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Passed ball; runners advance 1 base");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 37:
                situationOutcome.setText("SINGLE; beats out infield hit and spikes the firstbaseman; runners " +
                        "advance 1 base");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 38:
                situationOutcome.setText("Strikeout; catcher drops third strike; batter out at 1st; A-C PO-1B; " +
                        "runners advance 1 base");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Pop fly out; runners advance 1 base; PO-SS");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 40:
                situationOutcome.setText("Fly out; PO-RF runners advance 1 base after catch (S on 3rd out at home; " +
                        "A-RF PO-C DP)");
                if(thirdBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseDot.setVisible(false);
                    out += 2;
                    outsNum.setText(Integer.toString(out));
                    if(out >= 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseDot.setVisible(false);
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 41:
                situationOutcome.setText("Balk; runners advance one base");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingTwoManOnSecondAndThird(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; filling the bases");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("1st on error; E-LF; runners advance 1 base; batter to 2nd on throw home");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore ++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("SINGLE to center; one runner scores; other scores & batter to 2nd on " +
                        "wild throw; E-LF");
                thirdBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; one runner scores; other out at home; A-RF PO-C; batter" +
                        " to 2nd on throw home (F safe at home)");
                thirdBaseDot.setVisible(false);
                if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runners hold; E-SS; If 2 outs, runners advance 1 base");
                firstBaseDot.setVisible(true);
                if(out == 2) {
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 19:
                situationOutcome.setText("SINGLE; runners advance 1 base");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; runners advance 1 base; E-2B");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Out at 1st; runners hold; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 23:
                situationOutcome.setText("Triple play; rightfielder makes shoestring catch; PO-RF A-RF PO-2B A-2B PO-3B");
                threeOuts();
                break;
            case 24:
                situationOutcome.setText("High fly out; runners hold; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; line drive; runner doubled of 2nd; other holds 3rd; PO-1B " +
                        "A-1B PO-SS");
                secondBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Safe at 1st; FC; runner out at home; other to 3rd; A-2B A-C PO-3B");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runners hold; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("SINGLE thru short; both runners score");
                firstBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runners hold; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runners hold; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runners hold; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners on 3rd scores; other holds; PO-RF (F on 2nd goes to 3rd)");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                        secondBaseDot.setVisible(false);
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    } else {
                        thirdBaseDot.setVisible(false);
                    }
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("High fly out; runners hold; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Pop fly out; runners hold; PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-1B (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Passed ball; runners advance 1 base");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 37:
                situationOutcome.setText("SINGLE; beats out infield hit and spikes the firstbaseman; runners " +
                        "advance 1 base");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 38:
                situationOutcome.setText("Strikeout; catcher drops third strike; batter out at 1st; A-C PO-1B; " +
                        "runners advance 1 base");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Pop fly out; runners advance 1 base; PO-SS");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 40:
                situationOutcome.setText("1st on error; runners score; E-2B");
                firstBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 41:
                situationOutcome.setText("Balk; runners advance one base");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingThreeManOnSecondAndThird(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls; filling the bases");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("SINGLE to center; one runner scores; other scores & batter to 2nd on " +
                        "wild throw; E-LF");
                thirdBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("SINGLE to center; one runner scores; other scores & batter to 2nd on " +
                        "wild throw; E-CF");
                thirdBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; one runner scores; other out at home; A-RF PO-C; batter" +
                        " to 2nd on throw home (F safe at home)");
                thirdBaseDot.setVisible(false);
                if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runners hold; E-SS; If 2 outs, runners advance 1 base");
                firstBaseDot.setVisible(true);
                if(out == 2) {
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 19:
                situationOutcome.setText("1st on error; runners advance 1 base (F on 2nd scores) E-3B");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                if(secondBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 20:
                situationOutcome.setText("1st on error; runners advance 1 base; E-2B");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("1st on error; runners hold; E-3B");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
            case 23:
                situationOutcome.setText("Triple play; rightfielder makes shoestring catch; PO-RF A-RF PO-2B A-2B PO-3B");
                threeOuts();
                break;
            case 24:
                situationOutcome.setText("High fly out; runners hold; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; line drive; runner doubled of 2nd; other holds 3rd; PO-1B " +
                        "A-1B PO-SS");
                secondBaseDot.setVisible(false);
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("SINGLE thru 2nd; both runners score");
                firstBaseDot.setVisible(true);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 27:
                situationOutcome.setText("Out at 1st; runners hold; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Out at 1st; runners hold; A-SS PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Out at 1st; runners hold; A-P PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runner ont 3rd scores; other holds; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runners hold; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners hold; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Pop fly out; runners hold; PO-3B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("High fly out; runners hold; PO-SS");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Foul out; PO-1B (W-Base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        firstBaseDot.setVisible(true);
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Passed ball; runners advance 1 base");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 37:
                situationOutcome.setText("SINGLE; beats out bunt; runners advance 1 base");
                secondBaseDot.setVisible(false);
                firstBaseDot.setVisible(true);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 38:
                situationOutcome.setText("Strikeout; catcher drops third strike; batter out at 1st; A-C PO-1B; " +
                        "runners advance 1 base");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Pop fly out; runners advance 1 base; PO-SS");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 40:
                situationOutcome.setText("1st & 2nd on error; runners score; E-2B");
                thirdBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 41:
                situationOutcome.setText("Balk; runners advance one base");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; filling the bases");
                firstBaseDot.setVisible(true);
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeABasesLoaded(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN to deep center field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 4;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 4;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme left field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("TRIPLE to right field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("DOUBLE to left center; runners advance 2 bases; If 2 outs, all runners score");
                if(out == 2) {
                    firstBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 3;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 3;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 5:
                situationOutcome.setText("DOUBLE to right; 3 runners score (S on 1st out at home; A-RF PO-C)");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                } else {
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 3;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 3;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 6:
                situationOutcome.setText("TRIPLE over third");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE over 2nd; runners advance 2 bases");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("Fly out; 1 runner scores; others hold; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("Fly out; 1 runner scores; others hold; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE over 3rd; runners advance 2 bases; batter steals 2nd on next pitch" +
                        "; 1 ball on batter");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                ball = 1;
                ballsNum.setText(Integer.toString(ball));
                break;
            case 11:
                situationOutcome.setText("SINGLE past 2nd; runners advance 2 bases; batter steals 2nd on third pitch" +
                        " to next batter; 3 balls on batter ");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                ball = 3;
                ballsNum.setText(Integer.toString(ball));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeBBasesLoaded(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN to deep center field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 4;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 4;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme left field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("TRIPLE to right field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("DOUBLE to left center; runners advance 2 bases; If 2 outs, all runners score");
                if(out == 2) {
                    firstBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 3;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 3;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 5:
                situationOutcome.setText("DOUBLE to right; 3 runners score (S on 1st out at home; A-RF PO-C)");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                } else {
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 3;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 3;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 6:
                situationOutcome.setText("TRIPLE over third");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE over 2nd; runners advance 2 bases");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("Fly out; 1 runner scores; others hold; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE over 1st; runners advance 1 base; If 2 outs, runners advance 2 bases");
                if(out == 2) {
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE over 3rd; runners advance 2 bases; batter steals 2nd on next pitch" +
                        "; 1 ball on batter");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                ball = 1;
                ballsNum.setText(Integer.toString(ball));
                break;
            case 11:
                situationOutcome.setText("SINGLE past 2nd; runners advance 2 bases; batter steals 2nd on third pitch" +
                        " to next batter; 3 balls on batter ");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                ball = 3;
                ballsNum.setText(Integer.toString(ball));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeCBasesLoaded(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN to deep center field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 4;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 4;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme left field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("TRIPLE to right field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("DOUBLE to left center; runners advance 2 bases; If 2 outs, all runners score");
                if(out == 2) {
                    firstBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 3;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 3;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 5:
                situationOutcome.setText("DOUBLE to right; 3 runners score (S on 1st out at home; A-RF PO-C)");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                } else {
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 3;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 3;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 6:
                situationOutcome.setText("TRIPLE over third");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE over 2nd; runners advance 2 bases");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE thru short; runners advance 2 bases (S on 1st out at 3rd; A-LF PO-3B)");
                secondBaseDot.setVisible(false);
                if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                    }
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                } else {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("Fly out; 1 runner scores; others hold; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE over 3rd; runners advance 2 bases; batter steals 2nd on next pitch" +
                        "; 1 ball on batter");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                ball = 1;
                ballsNum.setText(Integer.toString(ball));
                break;
            case 11:
                situationOutcome.setText("SINGLE past 2nd; runners advance 2 bases; batter steals 2nd on third pitch" +
                        " to next batter; 3 balls on batter ");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                ball = 3;
                ballsNum.setText(Integer.toString(ball));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void gradeDBasesLoaded(int playerNumber) {
        switch (playerNumber) {
            case 1:
                situationOutcome.setText("HOMERUN to deep center field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    awayScore += 4;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    homeScore += 4;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 2:
                situationOutcome.setText("TRIPLE to extreme left field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 3:
                situationOutcome.setText("TRIPLE to right field");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 4:
                situationOutcome.setText("DOUBLE to left center; runners advance 2 bases; If 2 outs, all runners score");
                if(out == 2) {
                    firstBaseDot.setVisible(false);
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 3;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 3;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 5:
                situationOutcome.setText("DOUBLE to right; 3 runners score (S on 1st out at home; A-RF PO-C)");
                firstBaseDot.setVisible(false);
                thirdBaseDot.setVisible(false);
                if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                } else {
                    if(teamAtBat == 0) {
                        secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 3;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 3;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 6:
                situationOutcome.setText("TRIPLE over third");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                if(teamAtBat == 0) {
                    thirdBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 3;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    thirdBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 3;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 7:
                situationOutcome.setText("SINGLE over 2nd; runners advance 2 bases");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 8:
                situationOutcome.setText("SINGLE thru short; runners advance 2 bases (S on 1st out at 3rd; A-LF PO-3B)");
                secondBaseDot.setVisible(false);
                if(firstBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    thirdBaseDot.setVisible(false);
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                    }
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if(teamAtBat == 0) {
                            awayNextBatter();
                        } else {
                            homeNextBatter();
                        }
                    }
                } else {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 9:
                situationOutcome.setText("SINGLE over 1st; runners advance 1 base; If 2 outs, runners advance 2 bases");
                if(out == 2) {
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if(teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 10:
                situationOutcome.setText("SINGLE over 3rd; runners advance 2 bases; batter steals 2nd on next pitch" +
                        "; 1 ball on batter");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                ball = 1;
                ballsNum.setText(Integer.toString(ball));
                break;
            case 11:
                situationOutcome.setText("SINGLE past 2nd; runners advance 2 bases; batter steals 2nd on third pitch" +
                        " to next batter; 3 balls on batter ");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                ball = 3;
                ballsNum.setText(Integer.toString(ball));
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingOneBasesLoaded(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; runner out at home; batter out at 1st; A-SS PO-C A-C PO-1B");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("SINGLE to left; runners advance 2 bases (S on 2nd out at home; A-LF PO-C)");
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                secondBaseDot.setVisible(false);
                if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if (teamAtBat == 0) {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    }
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 16:
                situationOutcome.setText("SINGLE to center; runners advance 2 bases; batter out trying for second;" +
                        " A-CF A-1B PO-2B");
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                }
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; runners advance 1 base; If 2 outs, runners advance 2 bases");
                if(out == 2) {
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runners advance 2 bases; E-SS");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("Out at 1st; runners advance 1 base; A-3B PO-1B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 20:
                situationOutcome.setText("Hit by pitcher; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("1st on error-fumbled grounder; runners advance 1 base; E-1B");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Catcher picks runner off 1st; A-C PO-1B");
                firstBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                }
                break;
            case 23:
                situationOutcome.setText("Out at 1st; runners advance 1 base; A-1B PO-P");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    firstBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 24:
                situationOutcome.setText("Double play; runner out at home; batter out at 1st; A-SS PO-C A-C PO-1B");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; runner out at home; batter out at 1st; A-P PO-C A-C PO-1B");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Safe at 1st; FC; runner out at home; A-2B PO-C");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Double play; runner out at home; batter out at 1st; A-3B PO-C A-C PO-1B");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("SINGLE to short; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 29:
                situationOutcome.setText("Batter safe at 1st; FC; runner force out at home; others advance 1 base; " +
                        "A-P PO-C");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runners hold; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runners hold; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners on 2nd and 3rd advance 1 base; other holds; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Infield fly; PO-1B; runners hold");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Line drive out; runners hold; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Strikeout; PO-C (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch; runners advance 1 base");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 37:
                situationOutcome.setText("Fly out; all runners advance 1 base; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Infield fly; batter out; PO-SS; shortstop loses ball in sun; runners" +
                        " advance 1 base; If 2 outs, SINGLE; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(out == 2) {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if (teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Runner out stealing home; others advance 1 base; PO-C");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                }
                break;
            case 40:
                situationOutcome.setText("Base on balls");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 41:
                situationOutcome.setText("Triple play; PO-SS PO-SS A-SS PO-3B");
                threeOuts();
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingTwoBasesLoaded(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; batter out at 1st; runner out at 2nd; others advance 1 base;" +
                        " A-SS PO-2B A-2B PO-1B");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("SINGLE to left; runners advance 1 base; then another on error; E-LF");
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 16:
                situationOutcome.setText("1st on error; runners advance 2 bases; batter out trying for 2nd;" +
                        " E-CF A-CF PO-SS");
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(teamAtBat == 0) {
                    awayScore += 2;
                    updateAwayScore();
                } else {
                    homeScore += 2;
                    updateHomeScore();
                }
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; runners advance 1 base; If 2 outs, runners advance 2 bases");
                if(out == 2) {
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runners advance 2 bases; E-SS");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("SINGLE to left; runners advance 2 bases (S on 2nd out at home; A-LF PO-C)");
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                secondBaseDot.setVisible(false);
                if(secondBaseRunnerSpeed.equalsIgnoreCase("S")) {
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                    } else {
                        homeScore++;
                        updateHomeScore();
                    }
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if (teamAtBat == 0) {
                            firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                            awayNextBatter();
                        } else {
                            firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                            homeNextBatter();
                        }
                    }
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 20:
                situationOutcome.setText("Hit by pitcher; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("1st on error; runners advance 1 base; E-1B");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Catcher picks runner off 1st; A-C PO-1B");
                firstBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                }
                break;
            case 23:
                situationOutcome.setText("Hit by pitcher; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 24:
                situationOutcome.setText("Double play; runner out at home; batter out at 1st; A-SS PO-C A-C PO-1B");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; runner out at home; batter out at 1st; A-P PO-C A-C PO-1B");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Batter safe at 1st; FC; runner out at 2nd; others advance 1 base; A-2B PO-SS");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Batter safe at 1st; FC; runner force out at home; others advance 1 base; " +
                        "A-3B PO-C");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Safe at 1st; FC; runner out at home; A-SS PO-C");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Batter safe at 1st; FC; runner force out at home; others advance 1 base; " +
                        "A-P PO-C");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runner on 3rd scores; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                thirdBaseDot.setVisible(false);
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runners hold; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners on 2nd and 3rd advance 1 base; other holds; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Line drive out; runners hold; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Infield fly; PO-1B; runners hold");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Strikeout; PO-C (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch; runners advance 1 base");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 37:
                situationOutcome.setText("Fly out; all runners advance 1 base; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Infield fly; batter out; PO-SS; shortstop loses ball in sun; runners" +
                        " advance 1 base; If 2 outs, SINGLE; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(out == 2) {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if (teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Runner out stealing home; others advance 1 base; PO-C");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                }
                break;
            case 40:
                situationOutcome.setText("Base on balls");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 41:
                situationOutcome.setText("Triple play; PO-SS PO-SS A-SS PO-3B");
                threeOuts();
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void fieldingThreeBasesLoaded(int playerNumber) {
        switch (playerNumber) {
            case 12:
                situationOutcome.setText("Double play; batter out at 1st; runner out at 2nd; others advance 1 base;" +
                        " A-SS PO-2B A-2B PO-1B");
                firstBaseDot.setVisible(false);
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 13:
                situationOutcome.setText("Strikeout; PO-C");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 14:
                situationOutcome.setText("Base on balls");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 15:
                situationOutcome.setText("SINGLE to left; runners advance 2 bases (F on 1st scores)");
                if(firstBaseRunnerSpeed.equalsIgnoreCase("F")) {
                    thirdBaseDot.setVisible(false);
                    secondBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 3;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 3;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    secondBaseDot.setVisible(false);
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 16:
                situationOutcome.setText("SINGLE to right center field; runners advance 2 bases");
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                secondBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 17:
                situationOutcome.setText("SINGLE to right; runners advance 1 base; If 2 outs, runners advance 2 bases");
                if(out == 2) {
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore += 2;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore += 2;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 18:
                situationOutcome.setText("1st on error; runners advance 2 bases; E-SS");
                secondBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 19:
                situationOutcome.setText("1st & 2nd on error; runners advance 2 bases; E-3B");
                thirdBaseRunnerSpeed = firstBaseRunnerSpeed;
                firstBaseDot.setVisible(false);
                if (teamAtBat == 0) {
                    secondBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore += 2;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    secondBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore += 2;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 20:
                situationOutcome.setText("Hit by pitcher; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 21:
                situationOutcome.setText("1st on error; runners advance 1 base; E-1B");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 22:
                situationOutcome.setText("Catcher picks runner off 1st; A-C PO-1B");
                firstBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                }
                break;
            case 23:
                situationOutcome.setText("Hit by pitcher; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 24:
                situationOutcome.setText("Runner out at home; batter safe at 1st; A-SS PO-C");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 25:
                situationOutcome.setText("Double play; runner out at home; batter out at 1st; A-P PO-C A-C PO-1B");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out += 2;
                outsNum.setText(Integer.toString(out));
                if(out >= 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 26:
                situationOutcome.setText("Batter safe at 1st; FC; runner out at 2nd; others advance 1 base; A-2B PO-SS");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 27:
                situationOutcome.setText("Batter safe at 1st; FC; runner force out at home; others advance 1 base; " +
                        "A-3B PO-C");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 28:
                situationOutcome.setText("Batter safe at 1st; FC; runner out at 2nd; others advance 1 base; A-SS PO-2B");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseDot.setVisible(false);
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 29:
                situationOutcome.setText("Batter safe at 1st; FC; runner force out at home; others advance 1 base; " +
                        "A-P PO-C");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeNextBatter();
                    }
                }
                break;
            case 30:
                situationOutcome.setText("Fly out; runners hold; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 31:
                situationOutcome.setText("Fly out; runner on 3rd scores; PO-LF");
                out++;
                outsNum.setText(Integer.toString(out));
                thirdBaseDot.setVisible(false);
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 32:
                situationOutcome.setText("Fly out; runners on 2nd and 3rd advance 1 base; other holds; PO-RF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    secondBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 33:
                situationOutcome.setText("Infield fly; PO-1B; runners hold");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 34:
                situationOutcome.setText("Line drive out; runners hold; PO-2B");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    if (teamAtBat == 0) {
                        awayNextBatter();
                    } else {
                        homeNextBatter();
                    }
                }
                break;
            case 35:
                situationOutcome.setText("Strikeout; PO-C (W-base on balls)");
                if(teamAtBat == 0) {
                    if(homePitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            awayNextBatter();
                        }
                    }
                } else {
                    if(awayPitcherRating.equalsIgnoreCase("W")) {
                        thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                        secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    } else {
                        out++;
                        outsNum.setText(Integer.toString(out));
                        if(out == 3) {
                            threeOuts();
                        } else {
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 36:
                situationOutcome.setText("Wild pitch; runners advance 1 base");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(teamAtBat == 0) {
                    awayScore++;
                    updateAwayScore();
                } else {
                    homeScore++;
                    updateHomeScore();
                }
                break;
            case 37:
                situationOutcome.setText("Fly out; all runners advance 1 base; PO-CF");
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    threeOuts();
                } else {
                    firstBaseDot.setVisible(false);
                    thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                    secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                    if (teamAtBat == 0) {
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                }
                break;
            case 38:
                situationOutcome.setText("Infield fly; batter out; PO-SS; shortstop loses ball in sun; runners" +
                        " advance 1 base; If 2 outs, SINGLE; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if(out == 2) {
                    if (teamAtBat == 0) {
                        firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                        awayScore++;
                        updateAwayScore();
                        awayNextBatter();
                    } else {
                        firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                        homeScore++;
                        updateHomeScore();
                        homeNextBatter();
                    }
                } else {
                    firstBaseDot.setVisible(false);
                    out++;
                    outsNum.setText(Integer.toString(out));
                    if(out == 3) {
                        threeOuts();
                    } else {
                        if (teamAtBat == 0) {
                            awayScore++;
                            updateAwayScore();
                            awayNextBatter();
                        } else {
                            homeScore++;
                            updateHomeScore();
                            homeNextBatter();
                        }
                    }
                }
                break;
            case 39:
                situationOutcome.setText("Runner out stealing home; others advance 1 base; PO-C");
                firstBaseDot.setVisible(false);
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                out++;
                outsNum.setText(Integer.toString(out));
                if(out == 3) {
                    if(teamAtBat == 0) {
                        playerAtBatAway--;
                    } else {
                        playerAtBatHome--;
                    }
                    threeOuts();
                }
                break;
            case 40:
                situationOutcome.setText("Base on balls");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
            case 41:
                situationOutcome.setText("Triple play; PO-SS PO-SS A-SS PO-3B");
                threeOuts();
                break;
            case 42:
                situationOutcome.setText("Hit by pitcher; runners advance 1 base");
                thirdBaseRunnerSpeed = secondBaseRunnerSpeed;
                secondBaseRunnerSpeed = firstBaseRunnerSpeed;
                if (teamAtBat == 0) {
                    firstBaseRunnerSpeed = away.get(playerAtBatAway).getSpeed();
                    awayScore++;
                    updateAwayScore();
                    awayNextBatter();
                } else {
                    firstBaseRunnerSpeed = home.get(playerAtBatHome).getSpeed();
                    homeScore++;
                    updateHomeScore();
                    homeNextBatter();
                }
                break;
        }
        outcomeDescription = situationOutcome.getText();
    }

    private void awayNextBatter() {
        playerAtBatAway++;
        if(playerAtBatAway > 8) {
            playerAtBatAway = 0;
        }
        strike = 0;
        ball = 0;
        strikesNum.setText(Integer.toString(strike));
        ballsNum.setText(Integer.toString(ball));
    }

    private void homeNextBatter() {
        playerAtBatHome++;
        if(playerAtBatHome > 8) {
            playerAtBatHome = 0;
        }
        strike = 0;
        ball = 0;
        strikesNum.setText(Integer.toString(strike));
        ballsNum.setText(Integer.toString(ball));
    }

    private void threeOuts() {
        thirdBaseDot.setVisible(false);
        firstBaseDot.setVisible(false);
        secondBaseDot.setVisible(false);
        checkInning();
        if(teamAtBat == 0) {
            awayNextBatter();
            teamAtBat = 1;
            addInningToHomeLog();
        } else {
            homeNextBatter();
            teamAtBat = 0;
            addInningToAwayLog();
        }
        out = 0;
        strike = 0;
        ball = 0;
        strikesNum.setText(Integer.toString(strike));
        ballsNum.setText(Integer.toString(ball));
        outsNum.setText(Integer.toString(out));
    }

    private void updateAwayScore() {
        awayScoreLabel.setText(Integer.toString(awayScore));
    }

    private void updateHomeScore() {
        homeScoreLabel.setText(Integer.toString(homeScore));
    }

    private void checkInning() {
        if(teamAtBat != 0) {
            inning++;
        }
        if(inning == 9 && homeScore > awayScore && teamAtBat == 0) {
            gameOver();
        } else if(awayScore > homeScore && inning > 9 && teamAtBat != 0) {
            gameOver();
        } else if (inning > 9 && teamAtBat == 0 && homeScore > awayScore) {
            gameOver();
        } else if (inning > 9 && teamAtBat != 0 && awayScore > homeScore) {
            gameOver();
        } else{
            if(inningTopOrBot == 0) {
                inningTopOrBot = 1;
                inningNum.setText("Bottom " + Integer.toString(inning));
            } else {
                inningTopOrBot = 0;
                inningNum.setText("Top " + Integer.toString(inning));
            }
        }
    }

    private void checkIfHomeScoresInNinth() {
        if(homeScore > awayScore && inning == 9 && teamAtBat != 0) {
            gameOver();
        }
    }

    private void checkIfHomeScoresInExtraInnings() {
        if(homeScore > awayScore && inning > 9 && teamAtBat != 0) {
            gameOver();
        }
    }

    private void gameOver() {
        roll.setDisable(true);
        endedNaturally = true;
        outcomeDescription = situationOutcome.getText();
        if(teamAtBat == 0) {
            playerAtBatAway++;
            addToAwayLog();
        } else {
            playerAtBatHome++;
            addToHomeLog();
        }
        writeLogFile();
    }

    private void addInningToAwayLog() {
        awayLog += "Top of Inning " + Integer.toString(inning) + " - ";
    }

    private void addInningToHomeLog() {
        homeLog += "Bottom of Inning " + Integer.toString(inning) + " - ";
    }

    private void addToAwayLog() {
        int current = playerAtBatAway - 1;
        if(current < 0) {
            current = 8;
        }
        awayLog += away.get(current).getName() + ": " + outcomeDescription + "; ";
    }

    private void addToHomeLog() {
        int current = playerAtBatHome - 1;
        if(current < 0) {
            current = 8;
        }
        homeLog += home.get(current).getName() + ": " + outcomeDescription + "; ";
    }

    private void writeLogFile() {
        String finalScore = "FINAL SCORE: " + away.get(playerAtBatAway).getTeam() + ": " + awayScore + " " +
                home.get(playerAtBatHome).getTeam() + ": " + homeScore;
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        try {
            FileOutputStream output = new FileOutputStream(System.getProperty("user.dir") + "\\Logs\\" +
                    "Game" + strDate + ".txt");
            output.write(awayLog.getBytes());
            output.write(homeLog.getBytes());
            output.write(finalScore.getBytes());

            output.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
