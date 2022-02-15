package Quiz.Quiz;

public class PolicyDTO
{

       private String topic;
       private String question;
       private String answer;
       private String a;
       private String b;
       private String c;
       private String d;
       private String e;

        public PolicyDTO()
        {
            this.topic = "";
            this.question = "";
            this.answer = "";
            this.a = "";
            this.b = "";
            this.c = "";
            this.d = "";
            this.e = "";

        }

        public PolicyDTO(String topic, String question, String answer, String a, String b, String c, String d, String e)
        {
            this.topic = topic;
            this.question = question;
            this.answer = answer;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;

        }

        public void setPolicyDTO(String topic, String question, String answer, String a, String b, String c, String d, String e)
        {
            this.topic = topic;
            this.question = question;
            this.answer = answer;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;

        }

        public String GetTopic()
        {
            return topic;
        }

        public void setTopic(String Topic)
        {
            this.topic = Topic;
        }

        public String getQuestion()
        {
            return question;
        }

        public void setQuestion(String Question)
        {
            this.question = Question;
        }

        public String getAnswer()
        {
            return answer;
        }

        public void setAnswer(String Answer)
        {
            this.answer = Answer;
        }

        public String geta()
        {
            return a;
        }

        public void seta(String A)
        {
            this.a = A;
        }

        public String getb()
        {
            return b;
        }

        public void setb(String B)
        {
            this.b = B;
        }
        public String getc()
        {
            return c;
        }

        public void setc(String C)
        {
            this.c = C;
        }
        public String getd()
        {
            return d;
        }

        public void setd(String D)
        {
            this.d = D;
        }
        public String gete()
        {
            return e;
        }

        public void sete(String E)
        {
            this.e = E;
        }


}

