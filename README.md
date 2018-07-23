**Bindy Bug Test Repo**

Trying to achieve a CSV file in this format:

```
Header
Body
Body
Footer
Header
Body
Footer
Header
[…]
```

But Bindy gives strange results in my approach as soon as I add "Body".

The approach consists of:
 - CsvEntryHeader (Header)
 - CsvEntryBody (Body)
 - CsvEntryFooter (Footer)
 
 - CsvPackage (has Header/Bodies/Footer)
 
 

