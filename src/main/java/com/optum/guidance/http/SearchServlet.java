package com.optum.guidance.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.swapi.models.People;
import com.swapi.models.SWModelList;
import com.swapi.sw.StarWarsApi;



/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SearchServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		//pw.append("Served at: ").append(request.getContextPath()).append("\n");
		String searchContext = (String) request.getParameter("searchContext");
		StarWarsApi api = new StarWarsApi();
		SWModelList<?> swl = api.getSearchResultsForPeople(searchContext);
		ArrayList ap = swl.results;
		
		@SuppressWarnings("rawtypes")
		Iterator<LinkedHashMap> iterator = ap.iterator();
		while (iterator.hasNext()) {
			printMap(iterator.next(), pw);
		}
	}
		
	public void printMap(LinkedHashMap mp, PrintWriter pw) {
	    Iterator it = mp.entrySet().iterator();
	    pw.append("\n");
	    while (it.hasNext()) {
	        @SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry)it.next();
	        pw.append(pair.getKey() + " = " + pair.getValue()).append("\n");
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
        
	}

	


