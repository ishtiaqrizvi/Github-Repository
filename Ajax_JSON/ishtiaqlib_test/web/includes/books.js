
            //using DOM property selectedindex to use index of the selected option in drop down list.
            //if i ranging from 0 to e doesn't match key then selected property is used to change the selected state to false. 
            function checkSelected(e) {
                var key = e.selectedIndex;
                for(var i=0;i<e.length;i++) { 
                    if(i != key) { 
                        e[i].selected = false; 
                    } 
                }
            }
            
            //this function checks whether any Topic is entered or not.
            //if a topic is entered it stores topic to "search" and calls populateBooks function, giving search as argument.
            function findBooks(){
               var search = document.getElementById("topic").value.trim()
               if(search == ""){
                    targetList.options.length=0;
                    alert("Please enter topic");
                    return;
                }
                populateBooks(search) ;
            }
            
                //creating ajaxRequest an XMLHttpRequest object to send request call to the server using fetchbooks.java servlet to handle
                // the request and send a response.
                function populateBooks(searchTopic) {
               
                var ajaxRequest = new XMLHttpRequest();
                var url = "fetchBooks";
                //using onreadystatechange property of XMLHttpRequest to trigger a function when readystate is 4 and status is 200
                ajaxRequest.onreadystatechange = function() {
                    //readystate = 4 signifies that the request is send and response is ready 
                    //status = 200 signifies that the response is ready "OK".
                    //Both the conditions have to be checked
                    if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
                        //using JSON.parse to convert JSON text to javascript object.
                        var books = JSON.parse(ajaxRequest.responseText);
                        displayBooks(books); //calling displaybooks fucntion to display the response from the server
                    }
                }
                
                //using combination of open and send fucntions to send a request.
                ajaxRequest.open("POST", url, true);
                var searchParam = "topic=" + searchTopic;
                //alert("PARAMS"+ params);
                
                ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                ajaxRequest.setRequestHeader("Content-length", searchParam.length);
                ajaxRequest.setRequestHeader("Connection", "close");        
                  
                ajaxRequest.send(searchParam);
            }
            
            //function to display the response by using getElementbyID 
            function displayBooks(books) {
                var targetList = document.getElementById("lstBook") ;
                //using options.length to find out number of options in the drop down list
                targetList.options.length=0;  //initialise it to 0.
                
                for (var i = 0 ; i < books.Books.length ; i++) {
                    targetList.options[targetList.options.length] = new Option(books.Books[i].name, books.Books[i].id);                    
                }
                //if no books found then setting details to "".
                if (targetList.options.length==0) {   
                    targetList.options[targetList.options.length] = new Option('No books found', 0);
                     document.getElementById("details").innerHTML =  "" ;
                     document.getElementById("buyButton").style.visibility = "hidden" ;                    
                }
            }
            
            //this function finds the index of the selected option in the drop down list and saves the index value in bookId variable
            function bookDetails(buyBook) {
                var targetList = document.getElementById("lstBook") ;
                if (targetList.options.length  == 0) { 
                    return ;
                }

                if (targetList.options[targetList.selectedIndex].value == 0) {  // if user clicks on 'No books found' text
                    return ;
                }
                var bookId = targetList.options[targetList.selectedIndex].value;
                
                displayBookDetails(bookId, buyBook) ;  //calling displayBookDetails function
            }
            
            //this function sends request to the server to fetch book details using fetchbookdetails servlet
            function displayBookDetails(bookId, buyBook){
                var ajaxRequest = new XMLHttpRequest();
                var url = "fetchBookDetails";
        
                ajaxRequest.onreadystatechange = function() {
            
                    if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
                  
                      var book = JSON.parse(ajaxRequest.responseText);//converting JSON to javascript object
                      displayDetails(book); //calling function to display the response
                    }
                }
        
                ajaxRequest.open("POST", url, true);
                var searchParam = "book=" + bookId;
                //if book is bought, buy is set to YES and qty  is set to 1 else buy is set to NO
                if (buyBook)
                    searchParam = searchParam + "&buy=YES&qty=1";
                else
                    searchParam = searchParam + "&buy=NO";
                    
                ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                ajaxRequest.setRequestHeader("Content-length", searchParam.length);
                ajaxRequest.setRequestHeader("Connection", "close");        
                  
                ajaxRequest.send(searchParam);
                
            }
            
            //function to display the response.
            function displayDetails(book) {
               //if below condition is true book details desined as dets are displayed 
               if (book.Books.length > 0) {
                    
                    var dets = "Name : " +  book.Books[0].name + "<br/> Author : " + book.Books[0].author + "<br/> Price : " + book.Books[0].price + "<br/>"
                    dets = dets + " Available Qty: " + book.Books[0].qty   ;
                    
                    document.getElementById("buyButton").style.visibility = "visible" ;
                    if (parseInt(book.Books[0].qty) == 0 )  //if quantity is 0 then disable the button else enable the button.
                        document.getElementById("buyButton").disabled = true ;
                    else
                        document.getElementById("buyButton").disabled = false;
                    
                    document.getElementById("details").innerHTML =  dets ;
                }
                else {
                     document.getElementById("details").innerHTML =  "" ;
                     document.getElementById("buyButton").style.visibility = "hidden" ;
                }
                
            }
