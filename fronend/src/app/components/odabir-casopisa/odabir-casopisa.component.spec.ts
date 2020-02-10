import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OdabirCasopisaComponent } from './odabir-casopisa.component';

describe('OdabirCasopisaComponent', () => {
  let component: OdabirCasopisaComponent;
  let fixture: ComponentFixture<OdabirCasopisaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OdabirCasopisaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OdabirCasopisaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
