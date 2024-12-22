<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes"/>

    <!-- Template for the root element -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Authors List</title>
                <style>
                    table {
                    border-collapse: collapse;
                    width: 100%;
                    }
                    th, td {
                    border: 1px solid black;
                    padding: 8px;
                    text-align: left;
                    }
                    th {
                    background-color: #f2f2f2;
                    }
                </style>
            </head>
            <body>
                <h1>Authors</h1>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Birth Date</th>
                            <th>Country</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Loop through each author -->
                        <xsl:for-each select="authors/author">
                            <tr>
                                <td><xsl:value-of select="id"/></td>
                                <td><xsl:value-of select="name"/></td>
                                <td><xsl:value-of select="birth_date"/></td>
                                <td><xsl:value-of select="country"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>