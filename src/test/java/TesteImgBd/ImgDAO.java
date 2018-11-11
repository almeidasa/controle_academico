package TesteImgBd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @Autor Winder Rezende
 * @Data  27/10/2018
 */
public class ImgDAO {

    public void inserirImagem(String caminho) {
        File file = new File(caminho);
        InputStream fis;
        String SQL = "INSERT INTO imagem (foto) VALUES (?)";
        
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            fis = new FileInputStream(file);
            System.out.println((int) file.length());
            pstm.setBinaryStream(1, fis, (int) file.length());
            pstm.executeUpdate();
            pstm.close();
            BD.getConexao().close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Imagem Gravada no Banco de Dados!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Gravar Imagem!!!: \n" + ex);
        }
    }
    
    public void obterImargem(JLabel img, JComboBox boxCodImg){
        String SQL = "SELECT foto FROM imagem WHERE cod = (?)";
        
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            pstm.setInt(1, Integer.parseInt(boxCodImg.getSelectedItem().toString()));
            
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                byte[] imgBytes = ((byte[]) rs.getBytes("foto"));
                ImageIcon icon = new ImageIcon(imgBytes);
                img.setIcon(icon);
                
                try (FileOutputStream fos = new FileOutputStream("img/tempImg.jpg")) {
                    //fos.write((byte[]) rs.getBytes("foto"));
                    fos.write(rs.getBytes("foto"));
                }
            }

            pstm.close();
            BD.getConexao().close();
            System.out.println("Consulta Realizada na Tabela imagem!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Obter Imagem!!!: \n" + ex);
        }
    }
    
    public void obterCodigo(JComboBox txtCod){
        String SQL = "SELECT cod FROM imagem ORDER BY cod";
        
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                txtCod.addItem(rs.getString("cod"));
            }

            pstm.close();
            BD.getConexao().close();
            System.out.println("Consulta Realizada na Tabela imagem!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Obter Código!!!: \n" + ex);
        }
    }
    
    public void atulizarImargem(String caminho, JComboBox boxCodImg) {
        File file = new File(caminho);
        InputStream fis;
        String SQL = "UPDATE imagem SET foto = (?) WHERE cod = (?)";
        
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            fis = new FileInputStream(file);
            pstm.setBinaryStream(1, fis, (int) file.length());
            pstm.setInt(2, Integer.parseInt(boxCodImg.getSelectedItem().toString()));
            pstm.executeUpdate();
            pstm.close();
            BD.getConexao().close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Imagem Atualizada!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Atualizar Imagem!!!: \n" + ex);
        }
    }
    
    public void apagarImargem(JComboBox boxCodImg) {
        String SQL = "DELETE FROM imagem WHERE cod = (?)";
        
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, Integer.parseInt(boxCodImg.getSelectedItem().toString()));
            pstm.executeUpdate();
            pstm.close();
            BD.getConexao().close();
            JOptionPane.showMessageDialog(null, "Imagem Apagada do Banco de Dados!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Gravar Imagem!!!: \n" + ex);
        }
    }
}
