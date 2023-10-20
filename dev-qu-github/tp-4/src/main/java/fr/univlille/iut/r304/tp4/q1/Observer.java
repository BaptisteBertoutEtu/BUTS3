package main.java.fr.univlille.iut.r304.tp4.q1;

public interface Observer {

  public void update(Subject subj);
  public void update(Subject subj, Object data);

}
