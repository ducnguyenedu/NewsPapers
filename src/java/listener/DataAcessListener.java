/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import dal.NewsDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.News;

/**
 * Web application lifecycle listener.
 *
 * @author duchi
 */
public class DataAcessListener implements HttpSessionListener {

    ArrayList<News> newsPapers = new ArrayList<>();
    ArrayList<News> top5newsPapers = new ArrayList<>();
    ArrayList<HttpSession> sessions = new ArrayList<>();
    String shortContent = new String();

    public DataAcessListener() {
        updateData();
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("SessionCounter.sessionCreated");
        HttpSession session = event.getSession();
        sessions.add(session);
        session.setAttribute("newsPapers", newsPapers);
         session.setAttribute("top5newsPapers", top5newsPapers);
session.setAttribute("shortContent", shortContent);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("SessionCounter.sessionDestroyed");
        HttpSession session = event.getSession();
        sessions.remove(session);
    }

    public void updateData() {
        NewsDAO newsDAO = new NewsDAO();
        this.newsPapers = newsDAO.getNewsPapers();
        this.shortContent = (newsPapers.get(0).getContent().length()>300?
                (newsPapers.get(0).getContent().substring(0, 300))+"..." : (newsPapers.get(0).getContent()));
        for (int i = 0; i < 5; i++) {
            this.top5newsPapers.add(newsPapers.get(i));
            //System.out.println(i);
        }
        for (HttpSession session : sessions) {
            session.setAttribute("newsPapers", newsPapers);
            session.setAttribute("shortContent", shortContent);
            session.setAttribute("top5newsPapers", top5newsPapers);
        }
    }
}
