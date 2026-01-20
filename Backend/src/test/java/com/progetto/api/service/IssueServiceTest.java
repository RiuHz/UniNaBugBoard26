package com.progetto.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.progetto.api.repository.IssueRepository;
import com.progetto.interfaces.ImageStorageSaver;
import com.progetto.models.Immagine;
import com.progetto.models.Issue;

@ExtendWith(MockitoExtension.class)
public class IssueServiceTest {

	@Mock
	private IssueRepository issueRepository;
	@Mock
	private ImageStorageSaver imageStorage;

	@InjectMocks
	private IssueService issueService = new IssueService();

	private Issue issueConAllegato = new Issue();
	private Issue issueSenzaAllegato = new Issue();

	@BeforeEach
	void setUp() {
    	MultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "contenuto del file".getBytes());

		issueConAllegato.setAllegato(new Immagine());
		issueSenzaAllegato.setAllegato(new Immagine());

		issueConAllegato.getAllegato().setFile(multipartFile);
		issueSenzaAllegato.getAllegato().setFile(null);
	}

	@Test
	public void testSalvaIssueConAllegato() {
		assertDoesNotThrow(() ->
			when(imageStorage.saveImage(any(MultipartFile.class))).thenReturn("http://example.com/test.txt")
		);

		assertDoesNotThrow(() -> issueService.salvaIssue(issueConAllegato));

		assertThat(issueConAllegato.getAllegato().getUrl())
		.isNotNull();

		assertDoesNotThrow(() -> 
			verify(imageStorage, times(1)).saveImage(issueConAllegato.getAllegato().getFile())
		);

		verify(issueRepository, times(1)).save(issueConAllegato);
	}

	@Test
	public void testSalvaIssueSenzaAllegato() {
		assertDoesNotThrow(() -> issueService.salvaIssue(issueSenzaAllegato));

		assertThat(issueSenzaAllegato.getAllegato().getUrl())
		.isNull();

		assertDoesNotThrow(() -> 
			verify(imageStorage, times(0)).saveImage(issueSenzaAllegato.getAllegato().getFile())
		);

		verify(issueRepository, times(1)).save(issueSenzaAllegato);
	}
}
