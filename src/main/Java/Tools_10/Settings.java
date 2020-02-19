package Tools_10;

import java.io.Serializable;

/**
 * @author YXB
 * @date 2020/2/19 13:00
 */
public class Settings implements Serializable {

    private String database;                // 数据库名称
    private String table;                   // 数据表名称
    private String english_word_column;     // 英语单词 列
    private String chinese_meaning_column;  // 中文意思 列


    public Settings() {
    }

    public Settings(String database, String table, String english_word_column, String chinese_meaning_column) {
        this.database = database;
        this.table = table;
        this.english_word_column = english_word_column;
        this.chinese_meaning_column = chinese_meaning_column;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "database='" + database + '\'' +
                ", table='" + table + '\'' +
                ", english_word_column='" + english_word_column + '\'' +
                ", chinese_meaning_column='" + chinese_meaning_column + '\'' +
                '}';
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getEnglish_word_column() {
        return english_word_column;
    }

    public void setEnglish_word_column(String english_word_column) {
        this.english_word_column = english_word_column;
    }

    public String getChinese_meaning_column() {
        return chinese_meaning_column;
    }

    public void setChinese_meaning_column(String chinese_meaning_column) {
        this.chinese_meaning_column = chinese_meaning_column;
    }
}
