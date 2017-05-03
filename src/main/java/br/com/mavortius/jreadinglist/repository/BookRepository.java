package br.com.mavortius.jreadinglist.repository;

import br.com.mavortius.jreadinglist.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);
}
