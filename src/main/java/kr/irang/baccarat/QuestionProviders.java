package kr.irang.baccarat;

public class QuestionProviders {
    static {
        Questions.Q_Continue.setProvider(QuestionProvider.of("게임을 계속 하시겠습니까?",
                answers -> answers
                        .answer("Yes", () -> System.out.println("게임을 시작합니다."))
                        .answer("No", () -> System.out.println("게임을 종료합니다."))
        ));
        Questions.Q_BettingSide.setProvider(QuestionProvider.of("배팅 할 Side를 선택하세요.",
                answers -> answers
                        .answer("Player", () -> System.out.println("Player측에 배팅 할 금액을 입력하세요."))
                        .answer("Banker", () -> System.out.println("Banker측에 배팅 할 금액을 입력하세요."))
        ));
    }

    public static QuestionProvider of(Questions questions) {
        return questions.getProvider();
    }
}
