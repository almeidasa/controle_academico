package TesteImgBd;

import Util.Exibir;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.http.Part;

/**
 * @Autor Winder Rezende
 * @Data  10/11/2018
 */
public class imagemBean {

    private Part file;
    private String fileContent = "resources/img/usrFoto.jpg";
    private InputStream img;
    
    public void save() {
        try {
//            fileContent = new Scanner(file.getInputStream()).useDelimiter("//A").next();
//
//            System.out.println(fileContent);
            FileOutputStream fos = new FileOutputStream("D:\\Users\\Winder Rezende\\OneDrive\\Documentos\\NetBeansProjects\\controle_academico\\img\\.test.jpg");
            img = file.getInputStream();
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((bytesRead = img.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            fos.write(output.toByteArray());

        } catch (Exception e) {
            Exibir.Mensagem("Erro ao carregar arquivo: " + e);
        }
    }
    
    public void save2() {
        try {
            Path path = Paths.get("D:\\Users\\Winder Rezende\\OneDrive\\Documentos\\NetBeansProjects\\controle_academico\\img\\.test.jpg");
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            Exibir.Mensagem("Erro ao carregar arquivo: " + e);
        }
    }
}
