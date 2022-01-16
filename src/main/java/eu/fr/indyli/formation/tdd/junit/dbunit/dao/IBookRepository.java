package eu.fr.indyli.formation.tdd.junit.dbunit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Book;

@Repository("bookRepository")
public interface IBookRepository extends JpaRepository<Book, Long> {

  /**
   * Cherche par ISBN
   * 
   * @param isbn
   * @return
   */
  public Book findByIsbn(String isbn);
}
