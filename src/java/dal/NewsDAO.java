/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.News;

/**
 *
 * @author duchi
 */
public class NewsDAO extends DBContext {
    
    public boolean insertNews(News news) {
        boolean isSuc = false;
        
        try {
            String sql = "INSERT INTO [dbo].[News]\n"
                    + "           ([title]\n"
                    + "           ,[titleImg]\n"
                    + "           ,[content]\n"
                    + "           ,[writer]\n"
                    + "           ,[date])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            
            stm.setNString(1, news.getTitle());
            stm.setBytes(2, news.getImage());
            stm.setNString(3, news.getContent());
            stm.setNString(4, news.getWriter());
            stm.setDate(5, news.getDate());
            int count = stm.executeUpdate();
            isSuc = count > 0;
            System.out.println("dal.DAOGallery.insertGalleary()  " + count);
        } catch (SQLException ex) {
            Logger.getLogger(NewsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isSuc;
    }
    
    public ArrayList<News> getNewsPapers() {
        ArrayList<News> newsPapers = new ArrayList<>();
        
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[title]\n"
                    + "      ,[titleImg]\n"
                    + "      ,[content]\n"
                    + "      ,[writer]\n"
                    + "      ,[date]\n"
                    + "  FROM [dbo].[News]\n"
                    + "ORDER BY id DESC";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                News news = new News();
                int id = rs.getInt("id");
                news.setId(id);
                String title = rs.getString("title");
                news.setTitle(title);
                byte[] titleImg = rs.getBytes("titleImg");
               
                news.setImage(titleImg);
                String content = rs.getString("content");
                news.setContent(content);
                String writer = rs.getString("writer");
                news.setWriter(writer);
                Date date = rs.getDate("date");
                news.setDate(date);
                newsPapers.add(news);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newsPapers;
        
    }
}
