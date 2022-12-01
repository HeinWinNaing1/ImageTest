package controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Image;
import model.ImageDAO;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

/**
 * Servlet implementation class ImageController
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public static final String UPLOAD_DIR = "images";
	 public String dbFileName = "";
	
	 @Resource(name = "jdbc/imagetest")
	private DataSource dataSource;
	private ImageDAO imageDAO;
	
	@Override
	public void init() throws ServletException {
		imageDAO = new ImageDAO(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		createImage(request, response);
		String mode = request.getParameter("mode");
		if(mode == null) { 
			mode ="LIST";
		
		}
		switch (mode) {
		case "LIST":
			showList(request,response);
			break;
		case "CREATE":
			createImage(request, response);
			break;

		default:
			showList(request, response);
			break;
		}
		
	}
	
	
	private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Image> imageList = this.imageDAO.getImageList();
		request.setAttribute("imageList", imageList);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	private void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 
		Part part = request.getPart("image");
		String fileName = extractFileName(part);
		
		String applicationPath ="C:\\JavaEEWorkSpace\\ImageTest\\src\\main\\webapp\\";
//		String applicationPath = getServletContext().getRealPath("");
		String uploadPath = applicationPath+ UPLOAD_DIR;
		
		File fileUploadDir = new File(uploadPath);
		
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
		
		String savePath = uploadPath + File.separator + fileName;
		part.write(savePath );
		
		dbFileName = UPLOAD_DIR + File.separator + fileName;
        
 //       String s = request.getParameter("image");
        Image image = new Image(dbFileName,savePath);
        
        int rowEffected = this.imageDAO.createImage(image);
		
		while(rowEffected > 0) {
			showList(request, response);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	  private String extractFileName(Part part) {//This method will print the file name.
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                return s.substring(s.indexOf("=") + 2, s.length() - 1);
	            }
	        }
	        return "";
	    }
	
	

}
