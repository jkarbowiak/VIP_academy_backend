angular.module('app.books-management').factory('booksData', function ($http) {
    'use strict';

    return {
        getBooks: function (searchParams) {
            return $http.get('/services/books', {params: searchParams});
        },
        saveBook: function (book) {
            return $http.post('/services/book', book);
        },
        updateBook: function (book) {
            return $http.put('/services/book', book);
        },
        deleteBook: function (id) {
            return $http.delete('/services/book/' + id);
        }
    };
});
