package stuff;

import javax.swing.*;


public class Main extends JPanel
   implements ActionListener {
	private JTextField textFieldThreadURL;
	private JTextField textFieldBoardURL;
	
	JButton btnFolder;
	JFileChooser chooser;
	String foldername = "";
	JLabel labelFolder;
	
	JCheckBox chckbxImAwareThat;
	
	JButton btnCommenceLazor;
	String threadURL;
	String boardURL;
	
	JProgressBar progressBarTotal;
	JProgressBar progressBar;
	
	
	List<String> unexploredURL = new ArrayList<>();
	List<String> exploredThreadURL = new ArrayList<>();
	

   
	public Main () {
		setLayout(null);
		
		JLabel lblSelectOutputFolder = new JLabel("Select output folder");
		lblSelectOutputFolder.setBounds(27, 29, 154, 15);
		add(lblSelectOutputFolder);
		
		btnFolder = new JButton("Folder");
		btnFolder.addActionListener(this);
		btnFolder.setToolTipText("Make sure to have enough space for all that loli and CP");		
		btnFolder.setBounds(27, 56, 117, 25);
		add(btnFolder);
		
		progressBar = new JProgressBar();
		progressBar.setToolTipText("ze power level");
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		progressBar.setBounds(27, 514, 404, 31);
		add(progressBar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(27, 213, 404, 2);
		add(separator);
		
		JLabel lblThreadUrl = new JLabel("Thread URL");
		lblThreadUrl.setBounds(27, 116, 93, 15);
		add(lblThreadUrl);
		
		textFieldThreadURL = new JTextField();
		textFieldThreadURL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldBoardURL.setText("");
			}
		});
		textFieldThreadURL.setToolTipText("Example: http://boards.4chan.org/g/res/41013560");
		textFieldThreadURL.setBounds(27, 143, 255, 19);
		add(textFieldThreadURL);
		textFieldThreadURL.setColumns(10);
		
		JLabel lblDownloadTheWhole = new JLabel("Download the whole board");
		lblDownloadTheWhole.setBounds(27, 227, 202, 15);
		add(lblDownloadTheWhole);
		
		textFieldBoardURL = new JTextField();
		textFieldBoardURL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldThreadURL.setText("");
			}
		});
		textFieldBoardURL.setToolTipText("Example: http://4chan.org/b");
		textFieldBoardURL.setColumns(10);
		textFieldBoardURL.setBounds(27, 299, 255, 19);
		add(textFieldBoardURL);
		
		JLabel lblBoardUrl = new JLabel("Board letter");
		lblBoardUrl.setBounds(27, 272, 117, 15);
		add(lblBoardUrl);
		
		chckbxImAwareThat = new JCheckBox("I'm aware that I might get banned for this");
		chckbxImAwareThat.setToolTipText("... or accused of DoSing the site.");
		chckbxImAwareThat.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxImAwareThat.setBounds(27, 328, 355, 23);
		add(chckbxImAwareThat);
		
		btnCommenceLazor = new JButton("IMMA CHARGIN MAH LAZER");
		btnCommenceLazor.setToolTipText("RAPE IT NOW!");
		btnCommenceLazor.setBounds(100, 434, 260, 25);
		btnCommenceLazor.addActionListener(this);
		add(btnCommenceLazor);
		
		labelFolder = new JLabel("");
		labelFolder.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelFolder.setBounds(162, 61, 269, 15);
		add(labelFolder);
		
		progressBarTotal = new JProgressBar();
		progressBarTotal.setVisible(false);
		progressBarTotal.setStringPainted(true);
		progressBarTotal.setToolTipText("Total progress");
		progressBarTotal.setBounds(27, 483, 404, 31);
		add(progressBarTotal);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(27, 387, 54, 25);
		add(comboBox);
		
		JLabel lblNumberOfPages = new JLabel("Number of pages to download");
		lblNumberOfPages.setBounds(27, 359, 255, 16);
		add(lblNumberOfPages);
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnFolder) {
		    chooser = new JFileChooser(); 
		    chooser.setCurrentDirectory(new java.io.File("."));
		    chooser.setDialogTitle("Select ze lulz destination");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    //
		    // disable the "All files" option.
		    //
		    chooser.setAcceptAllFileFilterUsed(false);
		    //    
		    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
		      System.out.println("getCurrentDirectory(): " 
		         +  chooser.getCurrentDirectory());
		      System.out.println("getSelectedFile() : " 
		         +  chooser.getSelectedFile());
		      foldername = "" + chooser.getSelectedFile();
		      labelFolder.setText(foldername);
		      
		
		      }
		    else {
		      System.out.println("No Selection ");
		      
		    }
		}
		
		if(e.getSource() == btnCommenceLazor) {
			threadURL = textFieldThreadURL.getText();
			boardURL = textFieldBoardURL.getText();
			progressBar.setValue(0);
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			
			if (threadURL.matches("(http://boards.4chan.org/|https://boards.4chan.org/).*(/res/).*") && (!foldername.isEmpty() || foldername != "")) {
				
				downloadThread(threadURL);

			} else if (!boardURL.isEmpty() && (!foldername.isEmpty() || foldername != "")) {
				if (!chckbxImAwareThat.isSelected() ) {
					System.out.println("Got to check that!");
				} else {
					
					boardURL = "http://boards.4chan.org/" + boardURL;
					if (boardURL.matches("(http://boards.4chan.org/|https://boards.4chan.org/)([a-z0-9])")){
						
						System.out.println("Muahahha");
						
					    unexploredURL.add(boardURL);
					    
					    //TODO loenda mitu lehte boardil on
					    for (int i = 0; i< 16; i++){
					    	unexploredURL.add(boardURL + "/" + i);
					    }
					    
					    progressBarTotal.setVisible(true);
					    progressBarTotal.setValue(0);
					    crawl();
					    
					    unexploredURL.clear();
						exploredThreadURL.clear();
						progressBarTotal.setVisible(false);
					} else {
						System.out.println("Board url seems invalid!");
					}

				}
				

			} else {
				System.out.println("oops");
			}

		}
		
		
	
		
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		System.out.println("meh");
     }

  private void downloadThread(String threadURL2) {
		try {
  			Document doc = Jsoup.connect(threadURL2).get();

  			System.out.println(doc.body().select(".fileThumb"));
  			
  			Elements img = doc.body().select(".fileThumb");
  			
  			float progressBarValue = 0; 
  			float members = 0;
  			
  			for (Element el : img) {
  				members ++;                 
            }
  			
  			float counter = 1;
              for (Element el : img) {

                  //for each element get the src url
                  String src = el.absUrl("href");

                  System.out.println("Image Found!");
                  System.out.println("src attribute is : "+src);

                  getImages(src, foldername);
                  
                  
                  progressBarValue = (counter * (100/members));
                  progressBar.setValue(Math.round(progressBarValue));
                  Rectangle progressRect = progressBar.getBounds();//important line 
                  progressRect.x = 0;
                  progressRect.y = 0;         
                  progressBar.paintImmediately(progressRect);//important line 

                  counter ++;
              }
  			
  			
  		} catch (IOException a) {
  			a.printStackTrace();
  			
  		}
		
	}


public static void main(String s[]) {
	try {
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	} catch (Throwable e) {
		e.printStackTrace();
	}
    JFrame frame = new JFrame("4Chan Image Dowloader - DogeTech Systems");
    Main  panel = new Main ();
    frame.addWindowListener(
	    new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
			    System.exit(0);
		    }
	     }
     );
    frame.getContentPane().add(panel,"Center");
    frame.setSize(467, 600);
    frame.setVisible(true);
   

    
    
    }
  
  
  
  
  public void crawl() {
		for(int i=0; i<unexploredURL.size(); i++){
			
			exploreURL(unexploredURL.get(i));
			
			if (exploredThreadURL.size() > 1000) {
				System.out.println("Much police, doge cant risk!");
				break;
			}
		}
		System.out.println("Starting image dowloads;");
		
		float progressBarTotalValue = 0; 
		float members = exploredThreadURL.size();

		
		
		for(int i=0; i<exploredThreadURL.size(); i++){
			
            progressBarTotalValue = (i * (100/members));
            progressBarTotal.setValue(Math.round(progressBarTotalValue));
            Rectangle progressRect = progressBarTotal.getBounds();//important line 
            progressRect.x = 0;
            progressRect.y = 0;         
            progressBarTotal.paintImmediately(progressRect);//important line 
            
			downloadThread(exploredThreadURL.get(i));
			
			
		}
		
	}
  
  
	private void exploreURL(String url) {
		System.out.println("Uurib: "+url);
		
		try {
			Document doc = Jsoup.connect(url).get();
			Elements  threads= doc.select("div.thread");
			
			for(Element thread:threads){
				
				String newURL = thread.attr("id");
				newURL = boardURL + "/res/" + newURL.substring(1);
				System.out.println(newURL);
				exploredThreadURL.add(newURL);
				
				
				
			}
			
			
			
			System.out.println("\n-----------------------------------\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
  
  
  
  private static void getImages(String src, String folder) throws IOException {

      //Exctract the name of the image from the src attribute
      int indexname = src.lastIndexOf("/");

      if (indexname == src.length()) {
          src = src.substring(1, indexname);
      }

      indexname = src.lastIndexOf("/");
      String name = src.substring(indexname, src.length());

      System.out.println(name);

      //Open a URL Stream
      URL url = new URL(src);
      InputStream in = url.openStream();


		OutputStream out = new BufferedOutputStream(new FileOutputStream( folder+ name));

      for (int b; (b = in.read()) != -1;) {
          out.write(b);
      }
      out.close();
      in.close();
  }
  }
  
  
  
  
