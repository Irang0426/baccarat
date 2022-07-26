package kr.irang.baccarat.questions;

import java.util.Scanner;
import java.util.function.Consumer;

public class QuestionProvider extends BaseQuestionProvider {

    private QuestionProvider(String question, Consumer<QuestionOptions> options) {
        super(question, options);
    }
    private QuestionProvider(String question, String option, Runnable action) {
        super(question, option, action);
    }

    public static BaseQuestionProvider of(String question, Consumer<QuestionOptions> options) {
        return new QuestionProvider(question, options);
    }

    public static BaseQuestionProvider of(String question, String option, Runnable action) {
        return new QuestionProvider(question, option, action);
    }

    @Override
    protected void printQuestion(String question) {
        System.out.println(question);
    }

    @Override
    protected void printOptionAsSingle(String option) {
        System.out.println(option);
    }

    @Override
    protected void printOptionWithIndex(int index, String option) {
        System.out.println( index + 1 + ". " + option);
    }

    @Override
    protected int selectIndex() {
        return new Scanner(System.in).nextInt() - 1;
    }
}
