use sportsdata;
DELETE FROM element;
delete from entiti;
DELETE FROM element_type;
SET IDENTITY_INSERT [dbo].[element_Type] ON 

INSERT [dbo].[element_Type] ([id], [name], [created_at], [updated_at],[version]) VALUES (1, N'input', GETDATE(), NULL,1)
INSERT [dbo].[element_Type] ([id], [name], [created_at], [updated_at],[version]) VALUES (2, N'hidden', GETDATE(), NULL,1)
INSERT [dbo].[element_Type] ([id], [name], [created_at], [updated_at],[version]) VALUES (3, N'h3', GETDATE(), NULL,1)
INSERT [dbo].[element_Type] ([id], [name], [created_at], [updated_at],[version]) VALUES (4, N'password', GETDATE(), NULL,1)
INSERT [dbo].[element_Type] ([id], [name], [created_at], [updated_at],[version]) VALUES (5, N'checkbox', GETDATE(), NULL,1)
INSERT [dbo].[element_Type] ([id], [name], [created_at], [updated_at],[version]) VALUES (6, N'dropdown', GETDATE(), NULL,1)
INSERT [dbo].[element_Type] ([id], [name], [created_at], [updated_at],[version]) VALUES (7, N'textarea', GETDATE(), NULL,1)
INSERT [dbo].[element_Type] ([id], [name], [created_at], [updated_at],[version]) VALUES (8, N'file', GETDATE(), NULL,1)

SET IDENTITY_INSERT [dbo].[element_Type] OFF
select * from element_type;



SET IDENTITY_INSERT [dbo].[entiti] ON 
insert into entiti (id,name,created_at,version) 
values 
(1,'deporte',getdate(),1),
(2,'disciplina',getdate(),1)

SET IDENTITY_INSERT [dbo].[entiti] off
select * from entiti;

 


 
SET IDENTITY_INSERT element ON 
insert into element (id,entiti_id, idelement,label,pattern,pattern_message, is_unique, created_at,version, element_type_id, order_element) 
values 
-- DEPORTE
(1,1,'codigo','Código','([a-zA-Z0-9]{10})','Requiere un número de 10 dígitos',1,getdate(),1,1,1),
(2,1,'nombre','Nombre','.*([a-zA-Z0-9]{2}$','Requiere un texto o números, mínimo 2 caracteres',0,getdate(),1,1,2),

-- DISCIPLINA
(3,2,'codigo','Código','([a-zA-Z0-9]{10})','Requiere un número de 10 dígitos',1,getdate(),1,1,1),
(4,2,'nombre','Nombre','.*([a-zA-Z0-9]{2}$','Requiere un texto o números, mínimo 2 caracteres',0,getdate(),1,1,2)


SET IDENTITY_INSERT element off
select * from element;





