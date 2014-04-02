import org.apache.uima.UIMAFramework
import org.apache.uima.util.XMLInputSource
import org.apache.uima.cas.impl.XmiCasDeserializer
import org.apache.uima.util.CasCreationUtils
import org.apache.uima.resource.metadata.impl.TypePriorities_impl

import de.fraunhofer.scai.bio.uima.core.util.UIMAViewUtils
import de.fraunhofer.scai.bio.uima.core.util.UIMATypeSystemUtils

import de.fraunhofer.scai.bio.extraction.types.text.Section
import de.fraunhofer.scai.bio.extraction.types.text.CoreAnnotation
import de.fraunhofer.scai.bio.extraction.types.image.ImageAnnotation

object UIMAMain {
  def main(args: Array[String]) {
    val tsFile = new XMLInputSource(getClass.getResource("/SCAITypeSystem.xml"))
    val idxFile = new XMLInputSource(getClass.getResource("/SCAIIndexCollectionDescriptor.xml"))

    val typesystem = UIMAFramework.getXMLParser().parseTypeSystemDescription(
            tsFile);
    val indexes = UIMAFramework.getXMLParser().parseFsIndexCollection(idxFile)
            .getFsIndexes();

    val cas = CasCreationUtils.createCas(typesystem, new TypePriorities_impl(), indexes)
    cas.reset

    val document = getClass.getResourceAsStream("/US2732300A.pdf.xmi")

    XmiCasDeserializer.deserialize(document, cas)

    val doc = UIMAViewUtils.getOrCreatePreferredView(cas.getJCas, "DocumentView")
    // UIMAViewUtils.showAllViews(cas.getJCas)

    val sectionType = UIMATypeSystemUtils.getWantedType(doc, "Section", "de.fraunhofer.scai.bio.extraction.types.text")
    val annotationIndex = doc.getAnnotationIndex(sectionType)
    val it = annotationIndex.iterator()
    
    while(it.hasNext) {
      val annotation = it.next.asInstanceOf[CoreAnnotation]
      if (annotation != null) {
        if (annotation.isInstanceOf[Section]) {
          val sec = annotation.asInstanceOf[Section]
          println(sec.getConcept.getIdentifier)
          println(sec.getCoveredText)
        }
      }
    }

    tsFile.close
    idxFile.close
    document.close
  }
}