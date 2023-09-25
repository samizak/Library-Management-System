package com.librarymanagementsystem.service.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.librarymanagementsystem.util.Mapper;
import com.librarymanagementsystem.vo.AuthorRecord;
import com.librarymanagementsystem.vo.BookRecord;
import com.librarymanagementsystem.vo.CategoryRecord;
import com.librarymanagementsystem.vo.PublisherRecord;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.librarymanagementsystem.constant.Item;
import com.librarymanagementsystem.service.AuthorService;
import com.librarymanagementsystem.service.BookService;
import com.librarymanagementsystem.service.CategoryService;
import com.librarymanagementsystem.service.FileService;
import com.librarymanagementsystem.service.PublisherService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class FileServiceImpl implements FileService {

	final BookService bookService;

	final AuthorService authorService;

	final PublisherService publisherService;

	final CategoryService categoryService;

	public FileServiceImpl(BookService bookService, AuthorService authorService, PublisherService publisherService,
			CategoryService categoryService) {
		this.authorService = authorService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
		this.bookService = bookService;
	}

	@Override
	public void exportCSV(String fileName, HttpServletResponse response)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		var item = Item.getItemByValue(fileName);
		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + item.get().getFileName() + "\"");

		switch (item.get()) {
		case BOOK:
			StatefulBeanToCsv<BookRecord> writer1 = getWriter(response.getWriter());
			writer1.write(Mapper.bookModelToVo(bookService.findAllBooks()));
			break;
		case AUTHOR:
			StatefulBeanToCsv<AuthorRecord> writer2 = getWriter(response.getWriter());
			writer2.write(Mapper.authorModelToVo(authorService.findAllAuthors()));
			break;
		case CATEGORY:
			StatefulBeanToCsv<CategoryRecord> writer3 = getWriter(response.getWriter());
			writer3.write(Mapper.categoryModelToVo(categoryService.findAllCategories()));
			break;
		case PUBLISHER:
			StatefulBeanToCsv<PublisherRecord> writer4 = getWriter(response.getWriter());
			writer4.write(Mapper.publisherModelToVo(publisherService.findAllPublishers()));
			break;
		}

	}

	private static <T> StatefulBeanToCsv<T> getWriter(PrintWriter printWriter) {
		return new StatefulBeanToCsvBuilder<T>(printWriter).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false).build();
	}
}
