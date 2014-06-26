.PHONY: pdf, once, bib, move, clean

MAINTEX = Document
CHAPTER = Chapters/
TITLEPAGES = Titlepages/
BUILDPATH = _build/
BUILDNAME = S.Hodapp_2014_Masterarbeit

pdf:
	$(MAKE) once
	$(MAKE) bib
	$(MAKE) once
	$(MAKE) once
	$(MAKE) move
	echo "$$(tput setaf 0)$$(tput setab 2)Generated $(BUILDPATH)$(BUILDNAME).pdf$$(tput sgr 0)"

verbose:
	xelatex -file-line-error $(MAINTEX).tex

once:
	xelatex $(MAINTEX).tex 1> /dev/null

bib:
	bibtex $(MAINTEX).aux 1> /dev/null

move:
	mkdir -p $(BUILDPATH)
	mv $(MAINTEX).pdf $(BUILDPATH)$(BUILDNAME).pdf

open:
	open $(BUILDPATH)$(BUILDNAME).pdf

clean:
	rm -f $(CHAPTER)*.aux
	rm -f $(TITLEPAGES)*.aux
	rm -f $(MAINTEX).aux $(MAINTEX).toc $(MAINTEX).log $(MAINTEX).out
	rm -f $(MAINTEX).lof $(MAINTEX).bbl $(MAINTEX).blg
