/**
 * Created by Alice on 21.08.2016.
 */
public class Fakultaet {
  public static void main(String[] args) {
    System.out.println(fak(5));
  }

  static class Job {
    int n = 0;

    Job(int n) {
      this.n = n;
    }
  }

  private static Integer fak(Integer n) {
    SimpleLinkedStack<Job> jobs = new SimpleLinkedStack<Job>();
    jobs.push(new Job(n));
    Integer result = 1;
    while (!jobs.empty()) {
      Job job = jobs.pop();
      if (job.n > 1) {
        result *= job.n;
        jobs.push(new Job(job.n - 1));
      }
    }
    return result;
  }
}
