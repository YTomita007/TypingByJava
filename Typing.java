package Typing;

import Typing.Words.Word;
import Typing.Words.Kotowaza;
import Typing.Words.English;
import Typing.Words.Pokemon;

public class Typing {
    Word word;                  // 文字列ファイルの宣言

    private int score;          //スコアを計測
    private int miss;           //ミス回数
    private boolean gameFlg;    //ゲーム終了フラグ

    Typing(){                   // 初期化コンストラクタ
        score = 0;
        miss = 0;
        gameFlg = true;
    }

    // スコアセット・ゲット
    public void setScore(int score) {
        this.score += score; //ゲーム終了後にスコア取得
    }

    public int getScore() {
        return this.score; //スコアリターン
    }

    // ミス回数セット・ゲット
    public void setMiss(int miss) {
        this.miss += miss; //ミスをするたびに１加算
    }

    public int getMiss() {
        return this.miss; //ミス回数リターン
    }

    // ゲーム終了判定
    public void setGameFlg(boolean gameFlg) {
        this.gameFlg = gameFlg;
    }

    public boolean getGameFlg() {
        return this.gameFlg;
    }

    // 問題選択メソッド
    public void wordSelect() {
        System.out.println("【タイピングを実施したい内容を数字で選択して下さい】");
        System.out.println("『ことわざ : 1』");
        System.out.println("『英文書 : 2』");
        System.out.println("『ポケモン : 3』");

        try {
            @SuppressWarnings("resource")
            // どの文字列でタイピングするか入力値
            int num = new java.util.Scanner(System.in).nextInt();

            switch(num){
                case 1:
                    word = new Kotowaza();
                    break;
                case 2:
                    word = new English();
                    break;
                case 3:
                    word = new Pokemon();
                    break;
                default:
                    System.out.println("『1,2,3』のどれかを入力して下さい");
                    wordSelect();
            }
        }
        catch(Exception e) {
            System.out.println("数字で入力して下さい");
            wordSelect();
        }
    }

    // タイマーメソッド
    public void timer() {
        try {
            System.out.print("   3,");
            Thread.sleep(1000);
            System.out.print("   2,");
            Thread.sleep(1000);
            System.out.print("   1,");
            Thread.sleep(1000);
            System.out.println("  『START!!!!』\n------------------------------");
            
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    // リスタートメソッド
    public void reStart() {
        System.out.println("もう一度プレイしますか？");

        try {
            @SuppressWarnings("resource")
            int re= new java.util.Scanner(System.in).nextInt();
            if(re == 1) {
                setScore(-(getScore()));
                setGameFlg(true);
            }
            else if(re == 2) {
                System.out.println("お疲れ様でした。");
                setGameFlg(false);
            }
            else {
                System.out.println("1か2で入力してください");
                setGameFlg(false);
                reStart();
            }
        }
            catch(Exception e) {
                System.out.println("数字の1か2を入力して下さい");
                setGameFlg(false);
                reStart();
            }
        }

    // ことわざ１タイピングメソッド
    public void typingCheck() {
        for(int i = 0; i <20; i++) {
            // wordクラスから配列を呼び出しランダムで表示
            word.setWordRandom();
            System.out.println("【"+word.getWordConsole()+"】");

            // 
            @SuppressWarnings("resource")
            String in = new java.util.Scanner(System.in).nextLine();

            // 
            if(in.equals(word.getWordConsole())) {
                setScore(10);
            }

            else {
                setMiss(1);
                setScore(0);
            }
        }
        System.out.println("ゲーム終了です。\n[スコア]" + getScore() + "\n[ミス数]:" + getMiss());
    }
    
    // 
    public void typingGame() {
        while(getGameFlg()) {
            System.out.println("～タイピングゲームを開始します～");

            wordSelect();
            timer();

            typingCheck();
            reStart();
        }
    }
}
