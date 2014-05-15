/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livehereandnow.ages.engine;

//import com.livehereandnow.ages.card.Card;
import com.livehereandnow.ages.card.AgesCard;
import com.livehereandnow.ages.exception.AgesException;
import com.livehereandnow.ages.field.Field;
import com.livehereandnow.ages.field.Field.FieldPlayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mark
 */
public class NewEngine {

//    private EngineCore core;
    private Field field;
    private FieldPlayer player;
    private AgesCard card;

    public Field getField() {
        return field;
    }

    public NewEngine() throws AgesException {
        init();

    }

    private void init() {
        field = new Field();
    }

    String returnStr = " return str...";

    public String getFeedback() {
//        return core.getCardRowInfo();
//        return core.getFeedback();
//        return returnStr;
        return "...";
    }

    public void setFeedback(String str) {
        returnStr = str;
    }

    public boolean parser(String cmd) throws IOException, AgesException {
        //
        // 1. init
        //
        int tokenCnt = 0;//命令行裡共有幾個字，給予初值為0
        String keyword = "";//指令是什麼，給予初值空字符串
        int p1 = -1;//指令的參數是什麼，給予初值為-1，-1通常是指不能用的值
        int p2 = -1;
        int p3 = -1;

        //
        // 2. parser to words 
        //
        //將命令行的句子拆解為字，以空格格開為依據，空格都不記
        String[] strTokens = cmd.split(" ");
        List<String> tokens = new ArrayList<>();
        for (String item : strTokens) {
            if (item.length() > 0) {
                tokens.add(item);
            }
        }
        tokenCnt = tokens.size();//賦予變量tokenCnt真正的值，真正的值是指到底打個幾個字

        //
        // 3. to execute command based on size
        //
        if (tokenCnt == 0) {//when simple enter
            return true; // silently ignore it
        }
        // 
        keyword = tokens.get(0);//指令的關鍵字是第0個字，例如take 3的take

        if (tokenCnt == 1) {//如果輸入的是一個字的話
            return doCmd(keyword);
        }
        if (tokenCnt == 2) {//如果輸入的是2個字的話
            try {
                p1 = Integer.parseInt(tokens.get(1));
            } catch (Exception ex) {
                System.out.println("Parameter must be integer!");
                return false;
            }
            return doCmd(keyword, p1);
        }

        if (tokenCnt == 3) {//如果輸入的是2個字的話
            try {
                p1 = Integer.parseInt(tokens.get(1));
                p2 = Integer.parseInt(tokens.get(2));
            } catch (Exception ex) {
                System.out.println("Parameter must be integer!");
                return false;
            }
            return doCmd(keyword, p1, p2);
        }

        // ver 0.62 for upgrad 3 0 1, Upgrad Farm from Age A to Age I
        if (tokenCnt == 4) {//如果輸入的是3個字的話
            try {
                p1 = Integer.parseInt(tokens.get(1));
                p2 = Integer.parseInt(tokens.get(2));
                p3 = Integer.parseInt(tokens.get(3));
            } catch (Exception ex) {
                System.out.println("Parameter must be integer!");
                return false;
            }
            return doCmd(keyword, p1, p2, p3);
        }

        //
//        System.out.println("Cureently command must be one or two words only!");
        setFeedback("   unknown command," + cmd + ", just ignore it!");
//        setFeedback();

        return false;

    }

//    public EngineCore getCore() {
//        return core;
//    }
//    public Player getCurrentPlayer() {
//        return core.get當前玩家();
//    }
//    public String doProtocol(String cmd) throws IOException, AgesException {
////        core.getRoundNum();
//        switch (cmd) {
//            case "history":
//                return core.getHistory();
//            default:
//                return core.getCardRowInfo();
//        }
//
////        return core.NOCARD.toString();
//    }
//    public String doUserCmd(String user, String cmd) throws IOException, AgesException {
//
//        if (user.equalsIgnoreCase("admin")) {
//            parser(cmd);
//            return core.getFeedback();
//        }
//
//        if (core.get當前玩家().getName().equalsIgnoreCase(user)) {
//            if (parser(cmd)) {
//                return core.getFeedback();
//
//            } else {
//                return "unknown command, " + cmd;
//            }
//
//        } else {
//            return "   " + user + ", not your turn!";
//        }
//    }
    public boolean doCmd(int id) throws IOException, AgesException {
//        return field.
        field.showCardInfo(id);

        return true;
    }

    public boolean doCmd(String keyword) throws IOException, AgesException {
        switch (keyword) {
//           case "new-game"://v0.52
//               doNewGame();

//            case "brief":
//                return core.doBrief();
//            case "d"://v0.59
//            case "debug"://v0.59
//                return core.doDebug();
            case "start":
                return doStart();
            case "increase-population"://v0.52
            case "population"://v0.52
//                return core.doIncreasePopulation();
            case "revolution"://v0.39
//                return core.doRevolution();
            case "govt"://v0.39
            case "change-government"://v0.39
//                return core.doChangeGovernment();

            case "construct-wonder":
            case "wonder":
            case "w":
//                return core.doConstructWonder();

            case "farm": {
                System.out.println("請改用b 3 0");
//                return core.doFarm();
                return true;
            }
            case "help":
//                return core.doHelp();
            case "h":
//                return core.doHelpShort();
            case "s":
            case "status":
//                return core.doStatus();
                field.show();
                return true;
            case "version":
                return doVersion();

            case "change-turn":
            case "c":
            case "":
                return doChangeTurn();
            case "cc":

                return doChangeTurnV2();

            default:
                System.out.println("Unknown keyword, " + keyword);
                return false;
        }
    }

    public boolean doCmd(String keyword, int val) throws IOException, AgesException {
        switch (keyword) {
            case "status":
            case "s":
                return doStatus(val);

            case "打":
            case "p":
            case "o":
            case "out":

            case "play":
            case "play-card":
            case "out-card":
                return doPlayCard(val);
            case "oo":
//                return core.doPlayCard革命(val);
            case "拿"://在我的環境NetBeans無法執行，但是在DOS可以
            case "拿牌":
            case "t":
            case "take":
            case "take-card":
                return doTakeCard(val);

            default:
                System.out.println("Unknown keyword, " + keyword);
                return false;
        }
    }

    public boolean doCmd(String keyword, int p1, int p2) throws IOException, AgesException {
        switch (keyword) {
            case "build": // build a Mine, Farm, Urban Building, Military Unit 
            case "b":
//                return core.doBuild(p1, p2);
            case "destroy": // DESTROY a Mine, Farm, Urban Building
            case "disband": // DISBAND a Military Unit 
            case "d":
//                return core.doDestroy(p1, p2);

            case "produce":
//                case "player":
            case "打":
            case "out":
            case "o":

            case "play":
            case "play-card":   // PUT a LEADER INTO PLAY, PLAY AN ACTION CARD 
            //
            case "out-card":
//                return core.doPlayCard(p1, p2);

            default:
                System.out.println("Unknown keyword, " + keyword);
                return false;
        }
    }

    public boolean doCmd(String keyword, int p1, int p2, int p3) throws IOException, AgesException {
        switch (keyword) {
            case "upgrade":
            case "u":
//                return core.doUpgrade(p1, p2, p3);

            default:
                System.out.println("Unknown keyword, " + keyword);
                return false;
        }
    }

    public boolean doVersion() {
        //  System.out.println(" TODO   [A內政-亞歷山大圖書館 科技生產+1，文化生產+1，內政手牌上限+1，軍事手牌上限+1]  ");
        //getBuildingLimit()

        System.out.println("  === ver 0.5 ===  2014-5-12, 12:32, by Max　");
        System.out.println("    1.處理回合數內的生產");

        System.out.println("  === ver 0.4 ===  2014-5-12, 12:32, by Max　");
        System.out.println("    1.要處理回合數");

        System.out.println("  === ver 0.3 ===  2014-5-12, 11:45, by Max　");
        System.out.println("    1.準備作交換玩家");
        System.out.println("  === ver 0.2 ===  2014-5-12, 10:05, by Max　");
        System.out.println("    Done 需要增加setCurrentPlayer");
        System.out.println("    1.建立騎兵區、炮兵區、飛機區、劇院區、圖書館區、競技場區");
        System.out.println("  === ver 0.1 ===  2014-5-10, 16:47, by Max　");
        System.out.println("    1. 建立遊戲引擎的變量，能夠運作的地方");

        return true;
    }

    public String getCurrentPlayer() {
        return field.getCurrentPlayer().getName();
    }

    private void doNewGame() {
        field = new Field();
//        field.init();

//        field.getP1().get政府區().get(0).setTokenYellow(2);
    }

    private void 執行生產() {
        System.out.println("執行生產");
        int points;
        field.getCurrentPlayer().get文化().setPoints(field.getCurrentPlayer().get文化().getPoints() + field.getCurrentPlayer().get文化生產_當回合().getPoints());
        field.getCurrentPlayer().get科技().setPoints(field.getCurrentPlayer().get科技().getPoints() + field.getCurrentPlayer().get科技生產_當回合().getPoints());
//        for (int k = 0; k < field.getCurrentPlayer().get農場區().size(); k++) {
////            field.getCurrentPlayer().get農場區().get(k).produce();
////            field.getCurrentPlayer().get礦山區().get(k).produce();
//
//            points = field.getCurrentPlayer().get農場區().get(k).getTokenBlue() + field.getCurrentPlayer().get農場區().get(k).getTokenYellow();
//            field.getCurrentPlayer().get農場區().get(k).setTokenBlue(points);
//        }
//        for (int k = 0; k < field.getCurrentPlayer().get農場區().size(); k++) {
//            points = field.getCurrentPlayer().get礦山區().get(k).getTokenBlue() + field.getCurrentPlayer().get礦山區().get(k).getTokenYellow();
//            field.getCurrentPlayer().get礦山區().get(k).setTokenBlue(points);
//        }
//    ca.add(new AgesCard(2554, 1007, "農業", 0, "內政", "農場", "", "石頭:2", "食物:1", "null", "棕色", 1, 0, 0, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0));
    
        points = field.getCurrentPlayer().get農場區().get(0).getTokenBlue() + field.getCurrentPlayer().get農場區().get(0).getTokenYellow();
        field.getCurrentPlayer().get農場區().get(0).setTokenBlue(points);

//            points = field.getCurrentPlayer().get礦山區().get(k).getTokenBlue() + field.getCurrentPlayer().get礦山區().get(k).getTokenYellow();
//            field.getCurrentPlayer().get礦山區().get(k).setTokenBlue(points);
    }

    private boolean doPlayCard(int val) {

        System.out.println("...play-card ...");
//        if(field.getCurrentPlayer().get手牌內政牌區().wait(val, val))
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean doTakeCard(int val) {
        int[] cardCost = {1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3};
        int cost = cardCost[val];
        player = field.getCurrentPlayer();

        if (player.get內政點數().getPoints() < cardCost[cost]) {
            System.out.println("NOT ENOUGH 內政點數");
            return false;
        }

        AgesCard card = field.getCardRow().get(val);//宣告 card，並將卡牌列裡的指定編號的卡牌指定到這個card變量裡
        field.getCurrentPlayer().get手牌內政牌區().add(card);//當前玩家的手牌加入上一行的card
        field.getCardRow().remove(val);//從卡牌列拿掉剛剛那張card
        AgesCard temp = new AgesCard();
        temp.setId(1000);

        temp.setName("");
        temp.setAge(4);
        temp.setTag("");

        field.getCardRow().add(val, temp);//在卡牌列同樣的位子，補上一張空卡

//        field.
        player.pay內政點數(cost);
        return true;
    }

    private boolean doStart() {
        //System.out.println(" It has been transferred to init() , by Mark 5/12 17:50");
        field.getCurrentPlayer().get內政點數().setPoints(1);
        field.交換玩家();
        field.getCurrentPlayer().get內政點數().setPoints(2);
        field.交換玩家();
        System.out.println("Assign initial 內政點數 and 回合");
        for (int k = 0; k < 13; k++) {
            field.moveOneCard(field.get時代A內政牌(), 0, field.getCardRow());

//    
        }
//        field.get時代A內政牌().remove(0);
//        field.get時代A內政牌().remove(0);
//        field.get時代A內政牌().remove(0);
//        field.get時代A內政牌().remove(0);

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        for (int k = 0; k < 2; k++) {
//
//            player[k].get步兵區().get(0).setTokenYellow(1);
//            player[k].get實驗室().get(0).setTokenYellow(1);
//            player[k].get礦山區().get(0).setTokenYellow(2);
//            player[k].get農場區().get(0).setTokenYellow(2);
//            player[k].get人力庫_黃點().setPoints(18);
//            player[k].get工人區_黃點().setPoints(1);
//            player[k].get資源庫_藍點().setPoints(18);
//        }/*
//         field.getP1().get步兵區().get(0).setTokenYellow(1);
//         field.getP1().get實驗室().get(0).setTokenYellow(1);
//         field.getP1().get礦山區().get(0).setTokenYellow(2);
//         field.getP1().get農場區().get(0).setTokenYellow(2);
//         field.getP1().get人力庫_黃點().setPoints(18);
//         field.getP1().get工人區_黃點().setPoints(1);
//         field.getP1().get資源庫_藍點().setPoints(18);
//
//         field.getP2().get步兵區().get(0).setTokenYellow(1);
//         field.getP2().get實驗室().get(0).setTokenYellow(1);
//         field.getP2().get礦山區().get(0).setTokenYellow(2);
//         field.getP2().get農場區().get(0).setTokenYellow(2);
//         field.getP2().get人力庫_黃點().setPoints(18);
//         field.getP2().get工人區_黃點().setPoints(1);
//         field.getP2().get資源庫_藍點().setPoints(18);
//         */
        return true;
    }

    private boolean doChangeTurn() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

//        檢測暴動();
        compute當回合文化();
        compute當回合科技();

        this.執行生產();
//        腐敗();
//        field.getCurrentPlayer();
//        field.setCurrentPlayer(field.getP2());
//        交換玩家();
        field.交換玩家();
        beforeTurn();

        //        field.get
//    private List<Card> ageICivilCards;
//    ageICivilCards  = new ArrayList<>();
        return true;
    }

    private boolean doChangeTurnV2() {
        for (int k = 0; k < 38; k++) {
            doChangeTurn();
        }

        return true;
    }

    private void compute當回合文化() {
        System.out.println("compute當回合文化 ***TODO***");
        field.getCurrentPlayer().get文化生產_當回合().setPoints(2);
    }

    private void compute當回合科技() {
        System.out.println("compute當回合科技 ***TODO***");
        field.getCurrentPlayer().get科技生產_當回合().setPoints(3);
    }

    private boolean doStatus(int val) {
        field.show();
        return true;
    }

    private void beforeTurn() {
        if (field.getRound().getPoints() == 1) {
            return;
        }
        System.out.println(" ***TODO NEED TO COMPUTE 內政點數 FROM ALL RELATED CARDS, ASSISGN 4 FOR A WHILE");
        field.getCurrentPlayer().get內政點數().setPoints(4);
        field.getCurrentPlayer().get軍事點數().setPoints(6);

        System.out.println("在這裡作補牌");

//        移除前三張，實際作法是移除前三張增加三張三張空卡
        field.getCardRow().remove(0);
        field.getCardRow().remove(0);
        field.getCardRow().remove(0);
        AgesCard temp = new AgesCard();
        temp.setId(1000);
        temp.setName("");
        temp.setAge(4);
        temp.setTag("");
//        field.getCardRow().add(0, temp);//在卡牌列同樣的位子，補上一張空卡
//        field.getCardRow().add(0, temp);//在卡牌列同樣的位子，補上一張空卡

//      左推
        for (int k = 0; k < field.getCardRow().size(); k++) {
            if (field.getCardRow().get(k).getId() == 1000) {
                field.getCardRow().remove(k);
                k--;

            }
        }
        for (int k = field.getCardRow().size(); k < 13; k++) {

            if (field.get時代A內政牌().size() != 0) {
                field.moveOneCard(field.get時代A內政牌(), 0, field.getCardRow());
                for (int x = 0; x < field.get時代A內政牌().size(); x++) {
                    field.get時代A內政牌().remove(0);
                }
            } else if (field.get時代I內政牌().size() != 0) {
                field.moveOneCard(field.get時代I內政牌(), 0, field.getCardRow());
            } else if (field.get時代II內政牌().size() != 0) {
                field.moveOneCard(field.get時代II內政牌(), 0, field.getCardRow());
            } else if (field.get時代III內政牌().size() != 0) {
                //如果還有牌

                field.moveOneCard(field.get時代III內政牌(), 0, field.getCardRow());
            } else if (field.get時代III內政牌().size() == 0) {
                System.out.println("沒牌了");
                field.getCardRow().add(k, temp);//在卡牌列同樣的位子，補上一張空卡
                if (field.getCurrentPlayer() == field.getP1()) {
                    System.out.println("遊戲要結束了");
                } else {
                    System.out.println("遊戲下回合要結束了");
                }
            }
        }

    }

//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}
