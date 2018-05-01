   /**
    * Created by Catalysts on 8/9/2015.
    */
   function displayBooks(books) {
       var $tbody = $('tbody');
       $tbody.empty();
       for(var i in books) {
           var book = books[i];
           var $row = $('<tr>');
           $('<td>').html(book.id).appendTo($row);
           $('<td>').html(book.name).appendTo($row);
           $('<td>').html(book.isbn).appendTo($row);
           $('<td>').html(book.author).appendTo($row);
           $('<td>').html(book.genre).appendTo($row);
           $('<td>').html(book.quantity).appendTo($row);
           $('<td>').html(book.price).appendTo($row);

           $row.appendTo($tbody);
       }
   }

   function refreshBooks() {
       $.get('/books-all', {}, function(result) {
           displayBooks(result);
       });
   }

   function addBook(book) {
       $.ajax('/books-all', {
           headers: {
               'Accept': 'application/json',
               'Content-Type': 'application/json'
           },
           type: 'POST',
           data: JSON.stringify(book),
           dataType: 'json',
           success: function() {
               refreshBooks();
               $('#name, #isbn').val('');
           }
       });
   }

   $(function() {
       refreshBooks();
       $('button').click(function() {
           addBook({
               'name':     $('#name').val(),
               'author':   $('#author').val(),
               'isbn':     $('#isbn').val()
           });
           return false;
       });
   });