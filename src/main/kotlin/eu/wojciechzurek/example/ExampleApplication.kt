package eu.wojciechzurek.example

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

private val log = LoggerFactory.getLogger(ExampleApplication::class.java)

@SpringBootApplication
class ExampleApplication

fun main(args: Array<String>) {
    runApplication<ExampleApplication>(*args) {
        addInitializers(beans)
    }
}

val beans = beans {
    bean<BookService> {
        BookServiceImpl(ref())
    }
    bean<BookQuery>()
    bean<BookMutation>()
}

@Entity
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        val publicId: String,
        var title: String,
        var author: String
)

interface BookRepository : JpaRepository<Book, Long> {
    fun findByPublicId(publicId: String): Book
    fun existsByPublicId(publicId: String): Boolean
    fun deleteByPublicId(publicId: String)
}

interface BookService {
    fun getBook(publicId: String): Book
    fun getBooks(size: Int): List<Book>
    fun createBook(title: String, author: String): Book
    fun deleteBook(publicId: String): String?
    fun updateBook(publicId: String, title: String, author: String): Book
}

open class BookServiceImpl(private val bookRepository: BookRepository) : BookService {

    @Transactional(readOnly = true)
    override fun getBook(publicId: String) = bookRepository.findByPublicId(publicId)

    @Transactional(readOnly = true)
    override fun getBooks(size: Int): List<Book> =
            bookRepository.findAll(PageRequest.of(0, size, Sort.by(Sort.Direction.ASC, "id"))).content

    @Transactional
    override fun createBook(title: String, author: String) = bookRepository.save(Book(null, UUID.randomUUID().toString(), title, author))

    @Transactional
    override fun updateBook(publicId: String, title: String, author: String): Book {
        val book = bookRepository.findByPublicId(publicId)
        book.author = author
        book.title = title
        return bookRepository.save(book)
    }

    @Transactional
    override fun deleteBook(publicId: String): String? {
        if (bookRepository.existsByPublicId(publicId)) {
            bookRepository.deleteByPublicId(publicId)
            return publicId
        }
        return null
    }
}

class BookQuery(private val bookService: BookService) : GraphQLQueryResolver {
    fun getBook(publicId: String) = bookService.getBook(publicId)
    fun getBooks(size: Int) = bookService.getBooks(size)
}

class BookMutation(private val bookService: BookService) : GraphQLMutationResolver {
    fun createBook(title: String, author: String) = bookService.createBook(title, author)
    fun updateBook(publicId: String, title: String, author: String): Book = bookService.updateBook(publicId, title, author)
    fun deleteBook(publicId: String): String? = bookService.deleteBook(publicId)
}