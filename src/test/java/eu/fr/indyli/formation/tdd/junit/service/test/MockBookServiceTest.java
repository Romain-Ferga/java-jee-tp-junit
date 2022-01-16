package eu.fr.indyli.formation.tdd.junit.service.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import eu.fr.indyli.formation.tdd.junit.dbunit.app.Application;
import eu.fr.indyli.formation.tdd.junit.dbunit.dao.IAdherentRepository;
import eu.fr.indyli.formation.tdd.junit.dbunit.dao.IBookRepository;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Adherent;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Book;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.exception.BookException;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.impl.AdherentServiceImpl;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.impl.BookServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MockBookServiceTest {

  @InjectMocks
  private BookServiceImpl bookService;
  @InjectMocks
  private AdherentServiceImpl adherentService;
  @Mock
  private IBookRepository bookRepository = null;
  @Mock
  private IAdherentRepository adherentRepository = null;

  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Before
  public void initMock() {
    Long adId = 1L;
    Adherent a1 = new Adherent("SARKOZY", "NICOLAS", 65);
    when(adherentRepository.findById(adId)).thenReturn(Optional.of(a1));
  }

  @Test
  public void testGetBookById() {
    // Given
    Long bookId = 1L;
    Book mockBook = new Book("XXXXXX", 6);


    // When
    when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));
    Book book = this.bookService.findById(bookId);

    // Then
    assertNotNull(book);
    verify(bookRepository, times(1)).findById(bookId);
  }


  @Test
  public void testGetBookByIsbn() {
    // Given
    String isbnRent = "AGRFDSVC789K";
    // Book to Mock in repository Layer
    Book mockBook = new Book(isbnRent, 6);


    // When
    // TODO : Faites le necessaire pour que l'appel ci dessous retourune un objet non null
    Book book = this.bookService.findByIsbn(isbnRent);

    // Then
    assertNotNull(book);
  }

  @Test
  public void testRentBook() throws BookException {
    // Given
    String isbnRent = "AGRFDSVC789K";
    Adherent a1 = this.adherentService.findById(1L);
    Book mockBook = new Book(isbnRent, 1);

    // When
    // TODO : Instruction de Mock necessaire
    assertTrue(bookService.isBookAvalaible(isbnRent));
    bookService.rentBook(a1, isbnRent);

    // Then
    assertFalse(bookService.isBookAvalaible(isbnRent));
    assertTrue(bookService.getNbRequestCustomerByISBN(isbnRent) == 0);
  }


  // Test de location avec liste d'attente
  @Test
  public void testRentBookWithObserver() throws BookException {
    // Given
    String isbnRent = "AGRFDSVC789K";
    Adherent a1 = this.adherentService.findById(1L);
    Adherent a2 = this.adherentService.findById(2L);
    Book mockBook = new Book(isbnRent, 1);

    // When
    // Mockez le necessaire ci dessous

    assertTrue(bookService.isBookAvalaible(isbnRent));
    bookService.rentBook(a1, isbnRent);
    bookService.rentBook(a2, isbnRent);

    // Then
    assertFalse(bookService.isBookAvalaible(isbnRent));
    assertTrue(bookService.getNbRequestCustomerByISBN(isbnRent) > 0);
  }


  @Test
  public void testThrowExceptionIfISBNAbsent() throws BookException {
    // Spy
    thrown.expect(BookException.class);
    thrown.expectMessage("AUCUN BOUQUIN PORTANT CET ISBN");

    // Given
    Adherent a1 = this.adherentService.findById(1L);
    String isbn = "XXXXXXXXXXX";

    // When
    this.bookService.rentBook(a1, isbn);

    // Then
  }
}
