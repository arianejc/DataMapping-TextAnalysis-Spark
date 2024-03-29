# DataMapping-TextAnalysis-Spark
CrimeData Mapper &amp; Text Analysis Toolkit

In this project, the distributed context involves:

1. Crime Data Mapping: Scraping crime data from APIs and displaying it on a map requires distributing the data retrieval, processing, and visualization tasks across multiple nodes. Each node may be responsible for fetching data from specific APIs, processing the retrieved data, and presenting it on the map interface.

2. Text Analysis with Spark: Using Apache Spark for text analysis involves distributed computing, where large volumes of text data are distributed across multiple nodes in a cluster. Tasks such as counting words, symbols, distinct letters, and performing searches on text files are parallelized across the nodes to leverage distributed processing capabilities for improved performance and scalability.

**Tasks1:**

Utilize distributed systems and Google Earth to extract crime data from APIs and visualize it on a map.

**Task2:**
Employ Spark for the following tasks:
Task 1: Count the number of words in a text file.
Task 2: Determine the number of distinct words in a text file.
Task 3: Calculate the number of symbols (characters) in a text file.
Task 4: Identify the number of distinct symbols (characters) in a text file.
Task 5: Count the number of distinct letters (alphabets only) in a text file.
Task 6: Search for lines containing a specific word in the text file.
