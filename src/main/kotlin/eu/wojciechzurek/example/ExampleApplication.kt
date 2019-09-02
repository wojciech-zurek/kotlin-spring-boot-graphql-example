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
        val title: String,
        val author: String
)

interface BookRepository : JpaRepository<Book, Long> {
    fun findByPublicId(publicId: String): Book
}

interface BookService {
    fun getBook(publicId: String): Book
    fun getBooks(count: Int): List<Book>
    fun createBook(title: String, author: String): Book
}

class BookServiceImpl(private val bookRepository: BookRepository) : BookService {
    override fun getBook(publicId: String) = bookRepository.findByPublicId(publicId)

    override fun getBooks(count: Int): List<Book> =
            bookRepository.findAll(PageRequest.of(0, count, Sort.by(Sort.Direction.ASC, "id"))).content

    override fun createBook(title: String, author: String) = bookRepository.save(Book(null, UUID.randomUUID().toString(), title, author))
}

class BookQuery(private val bookService: BookService) : GraphQLQueryResolver {
    fun getBook(publicId: String) = bookService.getBook(publicId)
    fun getBooks(count: Int) = bookService.getBooks(count)
}

class BookMutation(private val bookService: BookService) : GraphQLMutationResolver {
    fun createBook(title: String, author: String) = bookService.createBook(title, author)
}