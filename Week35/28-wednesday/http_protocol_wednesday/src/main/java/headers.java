
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author runin
 */
@WebServlet(urlPatterns = {"/headers"})
public class headers extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Enumeration<String> headerNames = request.getHeaderNames(); //get all header names
        Map<String, String> headers = new LinkedHashMap(); //prepare key:value set to return. LinkedHashMap to keep insertion order.

        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement(); //grab curent element
            headers.put(name, request.getHeader(name)); //put current name:key value

            /* BELOW WAS DONE TO ADD SEVERAL HEADER VALUES, BUT IT DOES NOT SEEM NECESSARY!
            ALSO, map.put WILL REPLACE ANY PRIOR :name:-VALUES, SO THIS IS NOT GOOD ANYWAYS
             */
//            List<String> headerValues = Collections.list(request.getHeaders(name)); //one key (name) can have more values (headers) - grab all available
//
//            for (String value : headerValues) {
//                headers.put(name, value); //put all possible key:value pairs in the collection
//            }

        }
        //forward
        request.setAttribute("headers", headers);
        request.getRequestDispatcher("headers.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
