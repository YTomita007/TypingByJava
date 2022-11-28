package Typing.Words;

public class Word {
    // 問題配列変数宣言
    public String[] words;

    // ワード出力用変数
    public String wordConsole;

    // 引数に乱数を所得し配列をランダムで表示
    public void setWordRandom() {
        int num = new java.util.Random().nextInt(getWordLength());
        this.wordConsole = words[num];
    }

    // 配列値リターン
    public String getWordConsole() {
        return wordConsole;
    }

    // 配列の長さ取得
    public int getWordLength() {
        return words.length;
    }
}
