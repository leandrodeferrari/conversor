df_precios = pd.read_excel(excel_file)
df_precios = df_precios.rename(columns={'Unnamed: 0': 'Código', 'LOS PRECIOS NO INCLUYEN IVA, PRECIOS SUJETOS A MODIFICACIONES SIN PREVIO AVISO.': 'Precios'})
df_codigo = df_precios[['Código', 'Precios']]

df_codigo = df_codigo.dropna(subset=['Código', 'Precios'])
df_codigo = df_codigo[df_codigo['Código'].str.contains('\d')]
df_codigo['Precios con ganancia'] = df_codigo['Precios'].apply(lambda x: math.ceil(x * (1 + ganancia/100) / 50) * 50)

pdf_data = pdf_file.read()
pdf_buffer = io.BytesIO(pdf_data)
documento = f.open("pdf", pdf_buffer)

regex = r"\d{2,}/\d{2,}|/\d{2,}\b|\d{2,}/\b"
for numeroDePagina in range(len(documento)):
    pagina = documento.load_page(numeroDePagina)
    text = pagina.get_text("text")
    codigos = re.findall(regex, text)
        for codigo in codigos:
            precio_serie = df_codigo.loc[df_codigo['Código'] == codigo, 'Precios con ganancia']
            if not precio_serie.empty:
                precio = precio_serie.iloc[0]
                text_instances = pagina.search_for(codigo)
                for inst in text_instances:
                    bbox = inst.irect
                    new_y = bbox.y0 - 70
                    new_x = bbox.x0
                    pagina.insert_text((new_x, new_y), str(precio), fontsize=20, fill=(1, 0.3, 0), render_mode=2)

new_pdf_buffer = io.BytesIO()
documento.save(new_pdf_buffer)
new_pdf_buffer.seek(0)