package APBA;


public class Player {

    private String name;
    private String position;
    private String team;
    private String pitching_hand;
    private String pitching_grade;
    private String bat_hand;
    private String fielding_points;
    private String pitching_rating;
    private String speed;

    private String one_one;
    private String one_two;
    private String one_three;
    private String one_four;
    private String one_five;
    private String one_six;
    private String two_one;
    private String two_two;
    private String two_three;
    private String two_four;
    private String two_five;
    private String two_six;
    private String three_one;
    private String three_two;
    private String three_three;
    private String three_four;
    private String three_five;
    private String three_six;
    private String four_one;
    private String four_two;
    private String four_three;
    private String four_four;
    private String four_five;
    private String four_six;
    private String five_one;
    private String five_two;
    private String five_three;
    private String five_four;
    private String five_five;
    private String five_six;
    private String six_one;
    private String six_two;
    private String six_three;
    private String six_four;
    private String six_five;
    private String six_six;

    private String one_one2;
    private String one_two2;
    private String one_three2;
    private String one_four2;
    private String one_five2;
    private String one_six2;
    private String two_one2;
    private String two_two2;
    private String two_three2;
    private String two_four2;
    private String two_five2;
    private String two_six2;
    private String three_one2;
    private String three_two2;
    private String three_three2;
    private String three_four2;
    private String three_five2;
    private String three_six2;
    private String four_one2;
    private String four_two2;
    private String four_three2;
    private String four_four2;
    private String four_five2;
    private String four_six2;
    private String five_one2;
    private String five_two2;
    private String five_three2;
    private String five_four2;
    private String five_five2;
    private String five_six2;
    private String six_one2;
    private String six_two2;
    private String six_three2;
    private String six_four2;
    private String six_five2;
    private String six_six2;

    public Player() {
        this.name = null;
        this.position = null;
        this.team = null;
        this.pitching_hand = null;
        this.pitching_grade = null;
        this.bat_hand = null;
        this.fielding_points = null;
        this.pitching_rating = null;
        this.speed = null;

        this.one_one = null;
        this.one_two = null;
        this.one_three = null;
        this.one_four = null;
        this.one_five = null;
        this.one_six = null;
        this.two_one = null;
        this.two_two = null;
        this.two_three = null;
        this.two_four = null;
        this.two_five = null;
        this.two_six = null;
        this.three_one = null;
        this.three_two = null;
        this.three_three = null;
        this.three_four = null;
        this.three_five = null;
        this.three_six = null;
        this.four_one = null;
        this.four_two = null;
        this.four_three = null;
        this.four_four = null;
        this.four_five = null;
        this.four_six = null;
        this.five_one = null;
        this.five_two = null;
        this.five_three = null;
        this.five_four = null;
        this.five_five = null;
        this.five_six = null;
        this.six_one = null;
        this.six_two = null;
        this.six_three = null;
        this.six_four = null;
        this.six_five = null;
        this.six_six = null;

        this.one_one2 = null;
        this.one_two2 = null;
        this.one_three2 = null;
        this.one_four2 = null;
        this.one_five2 = null;
        this.one_six2 = null;
        this.two_one2 = null;
        this.two_two2 = null;
        this.two_three2 = null;
        this.two_four2 = null;
        this.two_five2 = null;
        this.two_six2 = null;
        this.three_one2 = null;
        this.three_two2 = null;
        this.three_three2 = null;
        this.three_four2 = null;
        this.three_five2 = null;
        this.three_six2 = null;
        this.four_one2 = null;
        this.four_two2 = null;
        this.four_three2 = null;
        this.four_four2 = null;
        this.four_five2 = null;
        this.four_six2 = null;
        this.five_one2 = null;
        this.five_two2 = null;
        this.five_three2 = null;
        this.five_four2 = null;
        this.five_five2 = null;
        this.five_six2 = null;
        this.six_one2 = null;
        this.six_two2 = null;
        this.six_three2 = null;
        this.six_four2 = null;
        this.six_five2 = null;
        this.six_six2 = null;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + " Position: " + this.getPosition() + " Fielding points: " +
                this.getFielding_points() + " Team: " + this.getTeam();
    }

    public Player(String name, String position, String team, String pitching_hand, String pitching_grade,
                  String bat_hand, String fielding_points, String pitching_rating, String speed, String one_one,
                  String one_two, String one_three, String one_four, String one_five, String one_six, String two_one,
                  String two_two, String two_three, String two_four, String two_five, String two_six, String three_one,
                  String three_two, String three_three, String three_four, String three_five, String three_six,
                  String four_one, String four_two, String four_three, String four_four, String four_five,
                  String four_six, String five_one, String five_two, String five_three, String five_four,
                  String five_five, String five_six, String six_one, String six_two, String six_three, String six_four,
                  String six_five, String six_six, String one_one2, String one_two2, String one_three2,
                  String one_four2, String one_five2, String one_six2, String two_one2, String two_two2,
                  String two_three2, String two_four2, String two_five2, String two_six2, String three_one2,
                  String three_two2, String three_three2, String three_four2, String three_five2, String three_six2,
                  String four_one2, String four_two2, String four_three2, String four_four2, String four_five2,
                  String four_six2, String five_one2, String five_two2, String five_three2, String five_four2,
                  String five_five2, String five_six2, String six_one2, String six_two2, String six_three2,
                  String six_four2, String six_five2, String six_six2) {
        this.name = name;
        this.position = position;
        this.team = team;
        this.pitching_hand = pitching_hand;
        this.pitching_grade = pitching_grade;
        this.bat_hand = bat_hand;
        this.fielding_points = fielding_points;
        this.pitching_rating = pitching_rating;
        this.speed = speed;

        this.one_one = one_one;
        this.one_two = one_two;
        this.one_three = one_three;
        this.one_four = one_four;
        this.one_five = one_five;
        this.one_six = one_six;
        this.two_one = two_one;
        this.two_two = two_two;
        this.two_three = two_three;
        this.two_four = two_four;
        this.two_five = two_five;
        this.two_six = two_six;
        this.three_one = three_one;
        this.three_two = three_two;
        this.three_three = three_three;
        this.three_four = three_four;
        this.three_five = three_five;
        this.three_six = three_six;
        this.four_one = four_one;
        this.four_two = four_two;
        this.four_three = four_three;
        this.four_four = four_four;
        this.four_five = four_five;
        this.four_six = four_six;
        this.five_one = five_one;
        this.five_two = five_two;
        this.five_three = five_three;
        this.five_four = five_four;
        this.five_five = five_five;
        this.five_six = five_six;
        this.six_one = six_one;
        this.six_two = six_two;
        this.six_three = six_three;
        this.six_four = six_four;
        this.six_five = six_five;
        this.six_six = six_six;

        this.one_one2 = one_one2;
        this.one_two2 = one_two2;
        this.one_three2 = one_three2;
        this.one_four2 = one_four2;
        this.one_five2 = one_five2;
        this.one_six2 = one_six2;
        this.two_one2 = two_one2;
        this.two_two2 = two_two2;
        this.two_three2 = two_three2;
        this.two_four2 = two_four2;
        this.two_five2 = two_five2;
        this.two_six2 = two_six2;
        this.three_one2 = three_one2;
        this.three_two2 = three_two2;
        this.three_three2 = three_three2;
        this.three_four2 = three_four2;
        this.three_five2 = three_five2;
        this.three_six2 = three_six2;
        this.four_one2 = four_one2;
        this.four_two2 = four_two2;
        this.four_three2 = four_three2;
        this.four_four2 = four_four2;
        this.four_five2 = four_five2;
        this.four_six2 = four_six2;
        this.five_one2 = five_one2;
        this.five_two2 = five_two2;
        this.five_three2 = five_three2;
        this.five_four2 = five_four2;
        this.five_five2 = five_five2;
        this.five_six2 = five_six2;
        this.six_one2 = six_one2;
        this.six_two2 = six_two2;
        this.six_three2 = six_three2;
        this.six_four2 = six_four2;
        this.six_five2 = six_five2;
        this.six_six2 = six_six2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPitching_hand() {
        return pitching_hand;
    }

    public void setPitching_hand(String pitching_hand) {
        this.pitching_hand = pitching_hand;
    }

    public String getPitching_grade() {
        return pitching_grade;
    }

    public void setPitching_grade(String pitching_grade) {
        this.pitching_grade = pitching_grade;
    }

    public String getBat_hand() {
        return bat_hand;
    }

    public void setBat_hand(String bat_hand) {
        this.bat_hand = bat_hand;
    }

    public String getFielding_points() {
        return fielding_points;
    }

    public void setFielding_points(String fielding_points) {
        this.fielding_points = fielding_points;
    }

    public String getPitching_rating() {
        return pitching_rating;
    }

    public void setPitching_rating(String pitching_rating) {
        this.pitching_rating = pitching_rating;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getOne_one() {
        return one_one;
    }

    public void setOne_one(String one_one) {
        this.one_one = one_one;
    }

    public String getOne_two() {
        return one_two;
    }

    public void setOne_two(String one_two) {
        this.one_two = one_two;
    }

    public String getOne_three() {
        return one_three;
    }

    public void setOne_three(String one_three) {
        this.one_three = one_three;
    }

    public String getOne_four() {
        return one_four;
    }

    public void setOne_four(String one_four) {
        this.one_four = one_four;
    }

    public String getOne_five() {
        return one_five;
    }

    public void setOne_five(String one_five) {
        this.one_five = one_five;
    }

    public String getOne_six() {
        return one_six;
    }

    public void setOne_six(String one_six) {
        this.one_six = one_six;
    }

    public String getTwo_one() {
        return two_one;
    }

    public void setTwo_one(String two_one) {
        this.two_one = two_one;
    }

    public String getTwo_two() {
        return two_two;
    }

    public void setTwo_two(String two_two) {
        this.two_two = two_two;
    }

    public String getTwo_three() {
        return two_three;
    }

    public void setTwo_three(String two_three) {
        this.two_three = two_three;
    }

    public String getTwo_four() {
        return two_four;
    }

    public void setTwo_four(String two_four) {
        this.two_four = two_four;
    }

    public String getTwo_five() {
        return two_five;
    }

    public void setTwo_five(String two_five) {
        this.two_five = two_five;
    }

    public String getTwo_six() {
        return two_six;
    }

    public void setTwo_six(String two_six) {
        this.two_six = two_six;
    }

    public String getThree_one() {
        return three_one;
    }

    public void setThree_one(String three_one) {
        this.three_one = three_one;
    }

    public String getThree_two() {
        return three_two;
    }

    public void setThree_two(String three_two) {
        this.three_two = three_two;
    }

    public String getThree_three() {
        return three_three;
    }

    public void setThree_three(String three_three) {
        this.three_three = three_three;
    }

    public String getThree_four() {
        return three_four;
    }

    public void setThree_four(String three_four) {
        this.three_four = three_four;
    }

    public String getThree_five() {
        return three_five;
    }

    public void setThree_five(String three_five) {
        this.three_five = three_five;
    }

    public String getThree_six() {
        return three_six;
    }

    public void setThree_six(String three_six) {
        this.three_six = three_six;
    }

    public String getFour_one() {
        return four_one;
    }

    public void setFour_one(String four_one) {
        this.four_one = four_one;
    }

    public String getFour_two() {
        return four_two;
    }

    public void setFour_two(String four_two) {
        this.four_two = four_two;
    }

    public String getFour_three() {
        return four_three;
    }

    public void setFour_three(String four_three) {
        this.four_three = four_three;
    }

    public String getFour_four() {
        return four_four;
    }

    public void setFour_four(String four_four) {
        this.four_four = four_four;
    }

    public String getFour_five() {
        return four_five;
    }

    public void setFour_five(String four_five) {
        this.four_five = four_five;
    }

    public String getFour_six() {
        return four_six;
    }

    public void setFour_six(String four_six) {
        this.four_six = four_six;
    }

    public String getFive_one() {
        return five_one;
    }

    public void setFive_one(String five_one) {
        this.five_one = five_one;
    }

    public String getFive_two() {
        return five_two;
    }

    public void setFive_two(String five_two) {
        this.five_two = five_two;
    }

    public String getFive_three() {
        return five_three;
    }

    public void setFive_three(String five_three) {
        this.five_three = five_three;
    }

    public String getFive_four() {
        return five_four;
    }

    public void setFive_four(String five_four) {
        this.five_four = five_four;
    }

    public String getFive_five() {
        return five_five;
    }

    public void setFive_five(String five_five) {
        this.five_five = five_five;
    }

    public String getFive_six() {
        return five_six;
    }

    public void setFive_six(String five_six) {
        this.five_six = five_six;
    }

    public String getSix_one() {
        return six_one;
    }

    public void setSix_one(String six_one) {
        this.six_one = six_one;
    }

    public String getSix_two() {
        return six_two;
    }

    public void setSix_two(String six_two) {
        this.six_two = six_two;
    }

    public String getSix_three() {
        return six_three;
    }

    public void setSix_three(String six_three) {
        this.six_three = six_three;
    }

    public String getSix_four() {
        return six_four;
    }

    public void setSix_four(String six_four) {
        this.six_four = six_four;
    }

    public String getSix_five() {
        return six_five;
    }

    public void setSix_five(String six_five) {
        this.six_five = six_five;
    }

    public String getSix_six() {
        return six_six;
    }

    public void setSix_six(String six_six) {
        this.six_six = six_six;
    }

    public String getOne_one2() {
        return one_one2;
    }

    public void setOne_one2(String one_one2) {
        this.one_one2 = one_one2;
    }

    public String getOne_two2() {
        return one_two2;
    }

    public void setOne_two2(String one_two2) {
        this.one_two2 = one_two2;
    }

    public String getOne_three2() {
        return one_three2;
    }

    public void setOne_three2(String one_three2) {
        this.one_three2 = one_three2;
    }

    public String getOne_four2() {
        return one_four2;
    }

    public void setOne_four2(String one_four2) {
        this.one_four2 = one_four2;
    }

    public String getOne_five2() {
        return one_five2;
    }

    public void setOne_five2(String one_five2) {
        this.one_five2 = one_five2;
    }

    public String getOne_six2() {
        return one_six2;
    }

    public void setOne_six2(String one_six2) {
        this.one_six2 = one_six2;
    }

    public String getTwo_one2() {
        return two_one2;
    }

    public void setTwo_one2(String two_one2) {
        this.two_one2 = two_one2;
    }

    public String getTwo_two2() {
        return two_two2;
    }

    public void setTwo_two2(String two_two2) {
        this.two_two2 = two_two2;
    }

    public String getTwo_three2() {
        return two_three2;
    }

    public void setTwo_three2(String two_three2) {
        this.two_three2 = two_three2;
    }

    public String getTwo_four2() {
        return two_four2;
    }

    public void setTwo_four2(String two_four2) {
        this.two_four2 = two_four2;
    }

    public String getTwo_five2() {
        return two_five2;
    }

    public void setTwo_five2(String two_five2) {
        this.two_five2 = two_five2;
    }

    public String getTwo_six2() {
        return two_six2;
    }

    public void setTwo_six2(String two_six2) {
        this.two_six2 = two_six2;
    }

    public String getThree_one2() {
        return three_one2;
    }

    public void setThree_one2(String three_one2) {
        this.three_one2 = three_one2;
    }

    public String getThree_two2() {
        return three_two2;
    }

    public void setThree_two2(String three_two2) {
        this.three_two2 = three_two2;
    }

    public String getThree_three2() {
        return three_three2;
    }

    public void setThree_three2(String three_three2) {
        this.three_three2 = three_three2;
    }

    public String getThree_four2() {
        return three_four2;
    }

    public void setThree_four2(String three_four2) {
        this.three_four2 = three_four2;
    }

    public String getThree_five2() {
        return three_five2;
    }

    public void setThree_five2(String three_five2) {
        this.three_five2 = three_five2;
    }

    public String getThree_six2() {
        return three_six2;
    }

    public void setThree_six2(String three_six2) {
        this.three_six2 = three_six2;
    }

    public String getFour_one2() {
        return four_one2;
    }

    public void setFour_one2(String four_one2) {
        this.four_one2 = four_one2;
    }

    public String getFour_two2() {
        return four_two2;
    }

    public void setFour_two2(String four_two2) {
        this.four_two2 = four_two2;
    }

    public String getFour_three2() {
        return four_three2;
    }

    public void setFour_three2(String four_three2) {
        this.four_three2 = four_three2;
    }

    public String getFour_four2() {
        return four_four2;
    }

    public void setFour_four2(String four_four2) {
        this.four_four2 = four_four2;
    }

    public String getFour_five2() {
        return four_five2;
    }

    public void setFour_five2(String four_five2) {
        this.four_five2 = four_five2;
    }

    public String getFour_six2() {
        return four_six2;
    }

    public void setFour_six2(String four_six2) {
        this.four_six2 = four_six2;
    }

    public String getFive_one2() {
        return five_one2;
    }

    public void setFive_one2(String five_one2) {
        this.five_one2 = five_one2;
    }

    public String getFive_two2() {
        return five_two2;
    }

    public void setFive_two2(String five_two2) {
        this.five_two2 = five_two2;
    }

    public String getFive_three2() {
        return five_three2;
    }

    public void setFive_three2(String five_three2) {
        this.five_three2 = five_three2;
    }

    public String getFive_four2() {
        return five_four2;
    }

    public void setFive_four2(String five_four2) {
        this.five_four2 = five_four2;
    }

    public String getFive_five2() {
        return five_five2;
    }

    public void setFive_five2(String five_five2) {
        this.five_five2 = five_five2;
    }

    public String getFive_six2() {
        return five_six2;
    }

    public void setFive_six2(String five_six2) {
        this.five_six2 = five_six2;
    }

    public String getSix_one2() {
        return six_one2;
    }

    public void setSix_one2(String six_one2) {
        this.six_one2 = six_one2;
    }

    public String getSix_two2() {
        return six_two2;
    }

    public void setSix_two2(String six_two2) {
        this.six_two2 = six_two2;
    }

    public String getSix_three2() {
        return six_three2;
    }

    public void setSix_three2(String six_three2) {
        this.six_three2 = six_three2;
    }

    public String getSix_four2() {
        return six_four2;
    }

    public void setSix_four2(String six_four2) {
        this.six_four2 = six_four2;
    }

    public String getSix_five2() {
        return six_five2;
    }

    public void setSix_five2(String six_five2) {
        this.six_five2 = six_five2;
    }

    public String getSix_six2() {
        return six_six2;
    }

    public void setSix_six2(String six_six2) {
        this.six_six2 = six_six2;
    }
}
