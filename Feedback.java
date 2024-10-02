public class Feedback<T>
{
    private T feedback;
    public Feedback(T feedback)
    {
        this.feedback=feedback;
    }

    public T getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return feedback.toString();
    }
}