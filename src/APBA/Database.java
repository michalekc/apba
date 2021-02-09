package APBA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Database {

    private Collection<Player> players;


    private Collection<Player> milwaukee = new ArrayList<>();
    private Collection<Player> chicagoC = new ArrayList<>();
    private Collection<Player> stlouis = new ArrayList<>();
    private Collection<Player> pittsburgh = new ArrayList<>();
    private Collection<Player> cincinnati = new ArrayList<>();
    private Collection<Player> atlanta = new ArrayList<>();
    private Collection<Player> newyorkM = new ArrayList<>();
    private Collection<Player> washington = new ArrayList<>();
    private Collection<Player> miami = new ArrayList<>();
    private Collection<Player> philadelphia = new ArrayList<>();
    private Collection<Player> sandiego = new ArrayList<>();
    private Collection<Player> sanfrancisco = new ArrayList<>();
    private Collection<Player> losangelesD = new ArrayList<>();
    private Collection<Player> colorado = new ArrayList<>();
    private Collection<Player> arizona = new ArrayList<>();
    private Collection<Player> toronto = new ArrayList<>();
    private Collection<Player> tampabay = new ArrayList<>();
    private Collection<Player> baltimore = new ArrayList<>();
    private Collection<Player> newyorkY = new ArrayList<>();
    private Collection<Player> boston = new ArrayList<>();
    private Collection<Player> cleveland = new ArrayList<>();
    private Collection<Player> detroit = new ArrayList<>();
    private Collection<Player> minnesota = new ArrayList<>();
    private Collection<Player> kansascity = new ArrayList<>();
    private Collection<Player> chicagoW = new ArrayList<>();
    private Collection<Player> seattle = new ArrayList<>();
    private Collection<Player> losangelesA = new ArrayList<>();
    private Collection<Player> oakland = new ArrayList<>();
    private Collection<Player> texas = new ArrayList<>();
    private Collection<Player> houston = new ArrayList<>();


    //Initialization of string array to store the results of a string separation
    private String[] parsed;
    //Expression to be used for separation
    private final String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    //Pattern result from compilation
    private Pattern compiledPattern = Pattern.compile(regex);

    void fill(File roster) {
        try {
            InputStream inputStream = new FileInputStream(roster);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            this.players = bufferedReader.lines().parallel().map(toPlayer).collect(Collectors.toCollection(ArrayList::new));

            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            //do nothing
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Player r : players) {
            if(r.getTeam().equalsIgnoreCase("Milwaukee Brewers")) {
                milwaukee.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Chicago Cubs")) {
                chicagoC.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Cincinnati Reds")) {
                cincinnati.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Pittsburgh Pirates")) {
                pittsburgh.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("St. Louis Cardinals")) {
                stlouis.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Colorado Rockies")) {
                colorado.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("San Francisco Giants")) {
                sanfrancisco.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Los Angeles Dodgers")) {
                losangelesD.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("San Diego Padres")) {
                sandiego.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Arizona Diamondbacks")) {
                arizona.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("New York Mets")) {
                newyorkM.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Philadelphia Phillies")) {
                philadelphia.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Miami Marlins")) {
                miami.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Washington Nationals")) {
                washington.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Atlanta Braves")) {
                atlanta.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Toronto Blue Jays")) {
                toronto.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Boston Red Sox")) {
                boston.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("New York Yankees")) {
                newyorkY.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Tampa Bay Rays")) {
                tampabay.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Baltimore Orioles")) {
                baltimore.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Houston Astros")) {
                houston.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Seattle Mariners")) {
                seattle.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Los Angeles Angels")) {
                losangelesA.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Texas Rangers")) {
                texas.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Oakland Athletics")) {
                oakland.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Detroit Tigers")) {
                detroit.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Cleveland Indians")) {
                cleveland.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Minnesota Twins")) {
                minnesota.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Kansas City Royals")) {
                kansascity.add(r);
            }
            if(r.getTeam().equalsIgnoreCase("Chicago White Sox")) {
                chicagoW.add(r);
            }
        }

    }

    private Function<String, Player> toPlayer = (line) -> {

        if(line.endsWith(",")) {
            line += '\0';
        }
        this.parsed = compiledPattern.split(line);

        return new Player(parsed[0], parsed[1], parsed[2], parsed[3], parsed[4], parsed[5], parsed[6], parsed[7], parsed[8],
                parsed[9], parsed[10], parsed[11], parsed[12], parsed[13], parsed[14], parsed[15], parsed[16], parsed[17],
                parsed[18], parsed[19], parsed[20], parsed[21], parsed[22], parsed[23], parsed[24], parsed[25], parsed[26],
                parsed[27], parsed[28], parsed[29], parsed[30], parsed[31], parsed[32], parsed[33], parsed[34], parsed[35],
                parsed[36], parsed[37], parsed[38], parsed[39], parsed[40], parsed[41], parsed[42], parsed[43], parsed[44],
                parsed[45], parsed[46], parsed[47], parsed[48], parsed[49], parsed[50], parsed[51], parsed[52], parsed[53],
                parsed[54], parsed[55], parsed[56], parsed[57], parsed[58], parsed[59], parsed[60], parsed[61], parsed[62],
                parsed[63], parsed[64], parsed[65], parsed[66], parsed[67], parsed[68], parsed[69], parsed[70], parsed[71],
                parsed[72], parsed[73], parsed[74], parsed[75], parsed[76], parsed[77], parsed[78], parsed[79], parsed[80]);
    };

    ObservableList<Player> getMilwaukee() {
        return milwaukee.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getChicagoCubs() {
        return chicagoC.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getPirates() {
        return pittsburgh.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getReds() {
        return cincinnati.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getCardinals() {
        return stlouis.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getBraves() {
        return atlanta.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getNationals() {
        return washington.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getMets() {
        return newyorkM.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getPhillies() {
        return philadelphia.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getMarlins() {
        return miami.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getPadres() {
        return sandiego.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getDBacks() {
        return arizona.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getRockies() {
        return colorado.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getDodgers() {
        return losangelesD.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getGiants() {
        return sanfrancisco.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getToronto() {
        return toronto.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getRedSox() {
        return boston.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getOrioles() {
        return baltimore.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getRays() {
        return tampabay.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getYankees() {
        return newyorkY.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getIndians() {
        return cleveland.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getTigers() {
        return detroit.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getTwins() {
        return minnesota.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getWhiteSox() {
        return chicagoW.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getRoyals() {
        return kansascity.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getAstros() {
        return houston.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getAs() {
        return oakland.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getMariners() {
        return seattle.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getAngels() {
        return losangelesA.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    ObservableList<Player> getRangers() {
        return texas.stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

}
