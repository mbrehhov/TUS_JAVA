package core;

import data.Question;
import data.Quiz;
import data.Score;
import data.Stats;
import entry.GameFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class GameManager {
    GameFrame gameFrame;

    List<Question> questionStack = new ArrayList<>();
    public GameManager(GameFrame gameFrame)
    {
        this.gameFrame = gameFrame;
    }
    private final Score highScore = new Score();

    public void getQuestionOption(Quiz quiz) {
        try {
            
        
            
            String question = quiz.readFile(-1, GameSingleton.getInstance().getJavaQuestions());
            int linenr = Integer.parseInt(question.substring(0, question.indexOf('.'))); // add some validations here
                                                                                         // later
            String options = quiz.readFile(linenr, GameSingleton.getInstance().getJavaOptions());

            Question newQuestion = new Question(question, splitOptions(options)) ;
               
            while(isInQuestionStack(newQuestion))
            {
                quiz = new Quiz();

                question = quiz.readFile(-1, GameSingleton.getInstance().getJavaQuestions());
                linenr = Integer.parseInt(question.substring(0, question.indexOf('.'))); // add some validations here
                                                                                             // later
                options = quiz.readFile(linenr, GameSingleton.getInstance().getJavaOptions());
    
                newQuestion = new Question(question, splitOptions(options)) ;
                
            }
            addToQuestionStack(newQuestion);
            
            
            String correctAnswer = quiz.readFile(linenr, GameSingleton.getInstance().getJavaAnswer());

            GameSingleton.getInstance().getGameStat().getJTextArea().setText(question);

            
            
            
            populateOptions(options);

            GameSingleton.getInstance().getGameStat()
                    .setQuestionInProcess(question);

            GameSingleton.getInstance().getGameStat()
                    .setCurrentQuestionAnswer(correctAnswer.substring(correctAnswer.length() - 1));

            // move(question, options);

        } catch (Exception e) {
            // ignore at the moment

        }
    }
    public synchronized String getName(float f)
    {
        return JOptionPane.showInputDialog(null, "you collected " + f + " points\n What is your name? ");
    }
    public void leaveGameToMainMenu() {
           
        // String name = 
         GameSingleton.getInstance().getGameStat().getTiming().stopLoop();



         try {
            GameSingleton.getInstance().getGameStat().getChildThread().join();

            GameSingleton.getInstance().getGameStat().getChildThread().interrupt();

        } catch (InterruptedException e) {
            //joined
        }
         Float f = GameSingleton.getInstance().getGameStat().getScore();
         highScore.evalueteTop(getName(f),f);

        
        GameSingleton.getInstance().getGameStat().setTiming(null);
        GameSingleton.getInstance().getGameStat().setChildThread(null);

        GameSingleton.getInstance().getGameStat().setScore(0);
        GameSingleton.getInstance().setGameStat(null);

        gameFrame.getMainFrame().remove(gameFrame.getGp().getMainp());
        gameFrame.getMainFrame().add(gameFrame.getIp().getIntroPanel());
        gameFrame.getMainFrame().repaint();
        gameFrame.getMainFrame().revalidate();

        //JOptionPane.showMessageDialog(null, "Game Finished, you collected " + f + " points");
           
    }

    public void newGame(JTextArea questions, Set<JRadioButton> options, List<JLabel> hearts, JLabel timeLabel) {
        Stats stats = GameSingleton.getInstance().getGameStat();

        if (stats == null || stats.isQuestionsEnabled()) {
            GameSingleton.getInstance().setGameStat(new Stats());
            GameSingleton.getInstance().getGameStat().setHearts(hearts);
            GameSingleton.getInstance().getGameStat().setTimeLabel(timeLabel);
            GameSingleton.getInstance().getGameStat().setQuestionsEnabled(false);
            GameSingleton.getInstance().getGameStat().setJTextArea(questions);
            GameSingleton.getInstance().getGameStat().setOptions(options);
            getQuestionOption(new Quiz());
            // questions.setText(Tools.getInstance().getGameStat().getQuestionInProcess());

            // enable hearts
            for (int i = 0; i <= GameSingleton.getInstance().getGameStat().getLives(); i++) {
                hearts.get(i).setVisible(true);
            }

            var time = new GameThread(this); // class that supports threading
            GameSingleton.getInstance().getGameStat().setTiming(time);
            var childThread = new Thread(time); // child thread
            childThread.start();
            GameSingleton.getInstance().getGameStat().setChildThread(childThread); // to access eventually

        }
    }

    public void answerToQuestion(char selection) {
        Stats stats = GameSingleton.getInstance().getGameStat();

        if (stats != null && stats.isQuestionsEnabled() == false) {

            // tmp solution
            GameSingleton.getInstance().getGameStat().setAnswerSubmited(true);
            GameSingleton.getInstance().getGameStat().getTimeLabel().setText("");

            if (GameSingleton.getInstance().getGameStat().getCurrentQuestionAnswer()
                    .equalsIgnoreCase(String.valueOf(selection))) {
                GameSingleton.getInstance().getGameStat().setScore(GameSingleton.getInstance().getGameStat().getScore()
                        + (float) GameSingleton.getInstance().getGameStat().getTiming().getTime());
                GameSingleton.getInstance().getGameStat().getTiming().setNewQuestion(true);

            } else {

                GameSingleton.getInstance().getGameStat().getTiming().setWrongAnswer(true);
            }

        }
    }
    private String[] splitOptions(String options)
    {
        return options.substring(options.indexOf("#") + 1).split("#");
    }

    private void populateOptions(String options) {
        String[] splittingOptions = splitOptions(options);

        int counter = 0;
        for (JRadioButton radioBtn : GameSingleton.getInstance().getGameStat().getOptions()) {
            if (!radioBtn.isEnabled()) {
                radioBtn.setEnabled(true);
            }
            radioBtn.setText(null);

            try {
                radioBtn.setText(splittingOptions[counter].substring(1));
                radioBtn.setName(splittingOptions[counter].substring(0, 1));

            } catch (Exception e) {
                // bad approach - change later
                if (radioBtn.isEnabled()) {
                    radioBtn.setEnabled(false);
                    radioBtn.setName(null);
                }

            }
            counter++;
        }

    }


    // if new question  is not in the question stack it can be used for user
    public void addToQuestionStack(Question question)
    {
        
        if(questionStack.size()==10)
        {
            questionStack.removeFirst();
        }

        questionStack.add(question);        
        // if question rached the limit, remove last one. 

    }

    public boolean isInQuestionStack(Question q)
    {
        for (Question question : questionStack) {
            if(question.equals(q))
            {
                return true;
            }
        }
      
        return false;
    }



}