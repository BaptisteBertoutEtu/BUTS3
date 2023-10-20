package main.java.fr.univlille.iutinfo.r304.utils;

public interface Observer {
        public void update(Subject subj);
        public void update(Subject subj, Object data);
}
